package com.emtp.phoneapp.orderservice.model;

import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class OrderDetails {

	public OrderDetails() {

	}

	public OrderDetails(@NotBlank final String id, @Positive final Integer amount) {
		this.id = id;
		this.amount = amount;
	}

	@NotBlank
	private String id;

	@Positive
	private Integer amount;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(final Integer amount) {
		this.amount = amount;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final OrderDetails that = (OrderDetails) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(amount, that.amount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, amount);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("OrderDetails{");
		sb.append("id='").append(id).append('\'');
		sb.append(", amount=").append(amount);
		sb.append('}');
		return sb.toString();
	}
}
