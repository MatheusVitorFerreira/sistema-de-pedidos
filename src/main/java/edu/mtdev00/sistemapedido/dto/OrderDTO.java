package edu.mtdev00.sistemapedido.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.mtdev00.sistemapedido.domain.Client;
import edu.mtdev00.sistemapedido.domain.Order;
import edu.mtdev00.sistemapedido.domain.OrderItems;
import edu.mtdev00.sistemapedido.domain.StatusPay;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OrderDTO {
	private Long id;
	private String clientName;
	private String cpfCNPJ;
	private String addressStreet;
	private String addressCep;
	private String addressCity;
	private StatusPay paymentStatus;
	private List<OrderItems> items;
	@JsonIgnore
	private AddressDTO address;
	private Double amountTotal;

	public OrderDTO(Order order, Client cliente, AddressDTO address, StatusPay paymentStatus) {
		this.id = order.getId();
		this.clientName = cliente.getName();
		this.cpfCNPJ = cliente.getCnpj() != null ? cliente.getCnpj() : cliente.getCpf();
		this.addressStreet = address.getStreet();
		this.addressCep = address.getCep();
		this.addressCity = address.getCity();
		this.items = new ArrayList<>(order.getItems());
		this.paymentStatus = paymentStatus;
		this.amountTotal = order.getAmountTotal();
	}

	public OrderDTO() {
	}


}
