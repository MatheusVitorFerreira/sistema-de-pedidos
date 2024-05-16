package edu.mtdev00.sistemapedido.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Order_Items") // Corrigido para corresponder ao nome real da tabela no banco de dados
public class OrderItems implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @JsonIgnore
    @EmbeddedId
    private ItemOrderPK id = new ItemOrderPK();
    
    private Integer quantity;
    private Double price; // Corrigido para corresponder ao nome real da coluna no banco de dados
    private Double discount;

    public OrderItems() {

    }

    public OrderItems(Order order, Product product, Integer quantity, Double price, Double discount) {
        id.setOrder(order);
        id.setProduct(product);
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }

    @JsonIgnore
    public Order getOrder() {
        return id.getOrder();
    }

    @JsonIgnore
    public double getAmountOrder() {
        // Removido: discount = 0.0;
        return (price - discount) * quantity;
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public ItemOrderPK getId() {
        return id;
    }

    public void setId(ItemOrderPK id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderItems other = (OrderItems) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));
        builder.append(getProduct().getName());
        builder.append(", quantity: ");
        builder.append(getQuantity());
        builder.append(", price:");
        builder.append(getPrice());
        builder.append(", Amount Items Total:");
        builder.append(nf.format(getAmountOrder()));
        return builder.toString();
    }
}
