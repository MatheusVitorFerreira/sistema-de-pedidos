package com.mtdev00.Sistema_Cadastro.Service;

import java.util.Date;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.mtdev00.Sistema_Cadastro.DTO.AddressDTO;
import com.mtdev00.Sistema_Cadastro.DTO.OrderDTO;
import com.mtdev00.Sistema_Cadastro.Domain.Address;
import com.mtdev00.Sistema_Cadastro.Domain.BoletoPayment;
import com.mtdev00.Sistema_Cadastro.Domain.Client;
import com.mtdev00.Sistema_Cadastro.Domain.Order;
import com.mtdev00.Sistema_Cadastro.Domain.OrderItems;
import com.mtdev00.Sistema_Cadastro.Domain.Payment;
import com.mtdev00.Sistema_Cadastro.Domain.Product;
import com.mtdev00.Sistema_Cadastro.Domain.StatusPay;
import com.mtdev00.Sistema_Cadastro.Resourche.BoletoService;
import com.mtdev00.Sistema_Cadastro.Service.TreatmentErros.InsufficientStockException;
import com.mtdev00.Sistema_Cadastro.repository.AddressRepository;
import com.mtdev00.Sistema_Cadastro.repository.ItemOrderRepository;
import com.mtdev00.Sistema_Cadastro.repository.OrderRepository;
import com.mtdev00.Sistema_Cadastro.repository.PaymentRepository;
import com.mtdev00.Sistema_Cadastro.repository.ProductRepository;

import jakarta.transaction.Transactional;

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

	public OrderDTO find(Integer id) {
		Order order = orderRepo.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "Order Not Found"));
		Client client = clientService.findClient(order.getClient().getId());
		@SuppressWarnings("unused")
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

	public List<Order> findAll() {
		return orderRepo.findAll();
	}

	@Transactional
	public Order insert(Order obj) {
		obj.setId(null);
		obj.setInstance(new Date());
		obj.getPayment().setOrder(obj);
		obj.getPayment().setStatus(StatusPay.PENDANT);

		if (obj.getPayment() instanceof BoletoPayment) {
			BoletoPayment pagtoB = (BoletoPayment) obj.getPayment();
			boletoService.fillPagamentoComBoleto(pagtoB, obj.getInstance());
		}
		Address address = addressRepo.findByClientId(obj.getClient().getId());
		obj.setAndressDelivery(address);

		// Validação prévia da quantidade de estoque
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

			// Atualização do estoque
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
