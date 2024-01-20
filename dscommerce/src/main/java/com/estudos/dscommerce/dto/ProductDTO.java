package com.estudos.dscommerce.dto;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

import com.estudos.dscommerce.entities.Category;
import com.estudos.dscommerce.entities.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDTO {

	private Long id;

	@NotBlank(message = "Campo requerido!")
	@Size(min = 3, max = 80, message = "O nome precisa ter de 3 a 80 caracteres")
	private String name;

	@NotBlank(message = "Campo obrigatório!")
	@Size(min = 10, message = "A descrição deve ter no mínimo 10 caracteres")
	private String description;

	@jakarta.validation.constraints.NotNull(message = "Campo requirido")
	@Positive(message = "O valor do preço deve ser positivo")
	private Double price;
	
	private String imgUrl;

	@jakarta.validation.constraints.NotEmpty(message = "Deve ter pelo menos uma categoria!")
	private List<CategoryDTO> categories = new ArrayList<>();

	public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;

	}

	public ProductDTO(Product product) {
		id = product.getId();
		name = product.getName();
		description = product.getDescription();
		price = product.getPrice();
		imgUrl = product.getImgUrl();
		
		for (Category cat : product.getCategories()) {
			categories.add(new CategoryDTO(cat));
		}
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public List<CategoryDTO> getCategories() {
		return categories;
	}

}
