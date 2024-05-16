
package edu.mtdev00.sistemapedido.domain;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Orders")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date instance;
	@ManyToOne
	@JoinColumn(name = "client.id")
	private Client client;

	@ManyToOne
	@JoinColumn(name = "addres_of_delivery_id")
	private Address andressDelivery;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
	private Payment payment;
	@OneToMany(mappedBy = "id.order", fetch = FetchType.EAGER)
	Set<OrderItems> items = new HashSet<>();

	public Order() {

	}

	public Order(Long id, Date instance, Client clients, Address andressDelivery) {
		this.id = id;
		this.instance = instance;
		this.client = clients;
		this.andressDelivery = andressDelivery;
	}

	public double getAmountTotal() {
		return items.stream().mapToDouble(OrderItems::getAmountOrder).sum();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getInstance() {
		return instance;
	}

	public void setInstance(Date instance) {
		this.instance = instance;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Address getAndressDelivery() {
		return andressDelivery;
	}

	public void setAndressDelivery(Address andressDelivery) {
		this.andressDelivery = andressDelivery;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Set<OrderItems> getItems() {
		return items;
	}

	public void setItems(Set<OrderItems> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		@SuppressWarnings("deprecation")
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		builder.append("Number Order: ");
		builder.append(getId());
		builder.append(client.getName());
		builder.append(", instance=");
		builder.append(sdf.format(getInstance()));
		builder.append(", client=");
		builder.append(getClient().getName());
		builder.append("\nStatus Payment: ");
		builder.append(getPayment().getStatus().getDescription());
		builder.append("\nDetails: \n");
		for (OrderItems ip : items) {
			builder.append(ip.toString());
			builder.append("\n");
		}
		builder.append("\nAmount Total: ");
		builder.append(nf.format(getAmountTotal()));
		return builder.toString();
	}

}