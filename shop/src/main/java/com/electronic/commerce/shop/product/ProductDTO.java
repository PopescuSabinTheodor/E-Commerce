package com.electronic.commerce.shop.product;

/**
 * Product DTO
 * @author Sabin Theodor
 *
 */

public class ProductDTO {
	

	
	private String name;
	
	private String description;
	
	private String specifications;
	
	private Integer stock;
	
	private Integer price;
	
	private Integer categoryId;
	



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

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", description=" + description
				+ ", specifications=" + specifications + ", stock=" + stock + ", price=" + price + ", categoryId="
				+ categoryId + ", image=]";
	}
	

	
}
