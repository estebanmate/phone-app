package com.emtp.phoneapp.catalogservice.model;

import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "phones")
public class Phone {

	@Id
	private String id;

	@NotBlank
	private String name;

	@NotBlank
	private String description;

	@URL
	@NotBlank
	private String catalogImage;

	@Positive
	private BigDecimal price;
	
	private int stock;
	
	public Phone () {
		
	}

	public Phone(final String id, final String name, final String description,
			final String catalogImage, final BigDecimal price, final int stock) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.catalogImage = catalogImage;
		this.price = price;
		this.stock = stock;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCatalogImage() {
		return catalogImage;
	}

	public void setCatalogImage(String catalogImage) {
		this.catalogImage = catalogImage;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Phone phone = (Phone) o;
		return Objects.equals(id, phone.id) && Objects.equals(name, phone.name)
				&& Objects.equals(description, phone.description)
				&& Objects.equals(catalogImage, phone.catalogImage)
				&& Objects.equals(price, phone.price)
				&& Objects.equals(stock, phone.stock);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, description, catalogImage, price, stock);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Phone{");
		sb.append("id=").append(id);
		sb.append(", name='").append(name).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", catalogImage='").append(catalogImage).append('\'');
		sb.append(", price=").append(price);
		sb.append(", stock=").append(stock);
		sb.append('}');
		return sb.toString();
	}
}
