package com.estudos.dscommerce.dto;

import com.estudos.dscommerce.entities.Product;

public class ProductMinDTO {

	private Long id;
	private String name;
	private Double price;
	private String imgUrl;
	
	public ProductMinDTO(Long id, String name, Double price, String imgUrl) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.imgUrl = imgUrl;
	}
	
	public ProductMinDTO(Product product) {
		id = product.getId();
		name = product.getName();
		price = product.getPrice();
		imgUrl = product.getImgUrl();
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
	}

	public String getImgUrl() {
		return imgUrl;
	}
	
}
