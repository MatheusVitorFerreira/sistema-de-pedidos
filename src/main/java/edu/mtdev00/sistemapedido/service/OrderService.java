package edu.mtdev00.sistemapedido.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import edu.mtdev00.sistemapedido.service.treatmenterros.InsufficientStockException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import edu.mtdev00.sistemapedido.dto.AddressDTO;
import edu.mtdev00.sistemapedido.dto.OrderDTO;
import edu.mtdev00.sistemapedido.domain.Address;
import edu.mtdev00.sistemapedido.domain.BoletoPayment;
import edu.mtdev00.sistemapedido.domain.CardPayment;
import edu.mtdev00.sistemapedido.domain.Client;
import edu.mtdev00.sistemapedido.domain.Order;
import edu.mtdev00.sistemapedido.domain.OrderItems;
import edu.mtdev00.sistemapedido.domain.Payment;
import edu.mtdev00.sistemapedido.domain.Product;
import edu.mtdev00.sistemapedido.domain.StatusPay;
import edu.mtdev00.sistemapedido.repository.AddressRepository;
import edu.mtdev00.sistemapedido.repository.ItemOrderRepository;
import edu.mtdev00.sistemapedido.repository.OrderRepository;
import edu.mtdev00.sistemapedido.repository.PaymentRepository;
import edu.mtdev00.sistemapedido.repository.ProductRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private PaymentRepository PaymentRepo;

	@Autowired
	private ClientService clientService;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ItemOrderRepository itemOrderRepository;

	@Autowired
	private AddressRepository addressRepo;
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private PlatformTransactionManager transactionManager;

	public OrderDTO find(Long id) {
		Order order = orderRepo.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "Order Not Found"));
		Client client = clientService.findClient(order.getClient().getId());
		Payment payment = PaymentRepo.findById(order.getPayment().getId())
				.orElseThrow(() -> new ObjectNotFoundException(id, "Payment Not Found"));
		Address address = addressRepo.findByClientId(client.getId());
		AddressDTO addressDto = new AddressDTO();
		if (address != null) {
			addressDto.setId(address.getId());
			addressDto.setStreat(address.getStreet());
			addressDto.setAddressSupplement(address.getAddressSupplement());
			addressDto.setCep(address.getCep());
			addressDto.setCity(address.getCity());
		}
		StatusPay paymentStatus = payment.getStatus();
		return new OrderDTO(order, client, addressDto, paymentStatus);
	}

	public Order convertToOrder(OrderDTO orderDTO) {
		Order order = new Order();
		order.setId(orderDTO.getId());
		return order;
	}

	public List<OrderDTO> findAll() {
		List<Order> orders = orderRepo.findAll();
		List<OrderDTO> orderDTOs = new ArrayList<>();

		for (Order order : orders) {
			Client client = clientService.findClient(order.getClient().getId());
			Payment payment = PaymentRepo.findById(order.getPayment().getId())
					.orElseThrow(() -> new ObjectNotFoundException(order.getId(), "Payment Not Found"));
			Address address = addressRepo.findByClientId(client.getId());
			AddressDTO addressDto = null;
			if (address != null) {
				addressDto = new AddressDTO();
				addressDto.setId(address.getId());
				addressDto.setStreat(address.getStreet());
				addressDto.setAddressSupplement(address.getAddressSupplement());
				addressDto.setCep(address.getCep());
				addressDto.setCity(address.getCity());
			}
			StatusPay paymentStatus = payment.getStatus();
			orderDTOs.add(new OrderDTO(order, client, addressDto, paymentStatus));
		}

		return orderDTOs;
	}

	@Transactional
	public Order insert(Order obj) {
		LocalDateTime now = LocalDateTime.now();
	    Date instanceDate = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
		obj.setId(null);
		obj.setInstance(instanceDate);
		obj.getPayment().setOrder(obj);
		obj.getPayment().setStatus(StatusPay.PENDANT);
		if (obj.getPayment() instanceof BoletoPayment) {
			BoletoPayment pagtoB = (BoletoPayment) obj.getPayment();
			pagtoB.setPaymentDate(instanceDate);
			boletoService.fillPagamentoComBoleto(pagtoB, obj.getInstance());
		}else {
			CardPayment pagtoC = (CardPayment) obj.getPayment();
			pagtoC.getParcelsAmount();

		}
		Address address = addressRepo.findByClientId(obj.getClient().getId());
		obj.setAndressDelivery(address);
		for (OrderItems ip : obj.getItems()) {
			Product product = productService.findProduct(ip.getProduct().getId());
			if (product.getStockQuantity() < ip.getQuantity()) {
				throw new InsufficientStockException(
						"There is no sufficient quantity of product " + product.getName() + " in stock!");
			}
		}
		obj = orderRepo.save(obj);
		PaymentRepo.save(obj.getPayment());

		for (OrderItems ip : obj.getItems()) {
			ip.setDiscount(0.0);
			ip.setPrice(productService.findProduct(ip.getProduct().getId()).getPrice());
			ip.setOrder(obj);
			int updatedQuantity = productRepo.updateStockQuantity(ip.getProduct().getId(), ip.getQuantity());
			if (updatedQuantity < 0) {
				transactionManager.rollback(TransactionAspectSupport.currentTransactionStatus());
				throw new InsufficientStockException(
						"There is no such quantity of product in stock!" + ip.getProduct().getName());
			}
		}
		itemOrderRepository.saveAll(obj.getItems());
		return obj;
	}

}
