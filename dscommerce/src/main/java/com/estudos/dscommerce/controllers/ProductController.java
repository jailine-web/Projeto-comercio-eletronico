package com.estudos.dscommerce.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.dscommerce.dto.ProductDTO;
import com.estudos.dscommerce.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping(value = "/{id}")
	public ProductDTO findbyId(@PathVariable Long id) {
		 
		ProductDTO dto = productService.findbyId(id);
		return dto;
	}
	
	@GetMapping()
	public List<ProductDTO> findAll() {
		return productService.findAll();
	}
	
}
