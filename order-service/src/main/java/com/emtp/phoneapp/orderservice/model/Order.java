package com.emtp.phoneapp.orderservice.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
public class Order {

	@Id
	private String id;

	@NotBlank
	private String customerName;

	@NotBlank
	private String customerSurname;

	@Email
	@NotBlank
	private String email;
	
	private BigDecimal totalPrice;

	@NotEmpty
	private List<OrderDetails> orderDetails;
	
	public Order() {
		
	}

	public Order(String id, @NotBlank String customerName, @NotBlank String customerSurname,
			@Email @NotBlank String email, @NotEmpty List<OrderDetails> orderDetails, BigDecimal totalPrice) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.customerSurname = customerSurname;
		this.email = email;
		this.orderDetails = orderDetails;
		this.totalPrice = totalPrice;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerSurname() {
		return customerSurname;
	}

	public void setCustomerSurname(String customerSurname) {
		this.customerSurname = customerSurname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Order phone = (Order) o;
		return Objects.equals(id, phone.id) && Objects.equals(email, phone.email)
				&& Objects.equals(customerName, phone.customerName)
				&& Objects.equals(customerSurname, phone.customerSurname)
				&& Objects.equals(orderDetails, phone.orderDetails);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, email, customerName, customerSurname, orderDetails);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Order{");
		sb.append("id=").append(id);
		sb.append(", customerName=").append(customerName);
		sb.append(", customerSurname=").append(customerSurname);
		sb.append(", email=").append(email);
		sb.append(", totalPrice=").append(totalPrice);
		sb.append(", orderDetails=").append(orderDetails).append("}");
		return sb.toString();
	}



}
