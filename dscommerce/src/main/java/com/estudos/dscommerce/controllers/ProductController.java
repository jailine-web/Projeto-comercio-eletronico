package com.estudos.dscommerce.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public Page<ProductDTO> findAll(Pageable pageable) {
		return productService.findAll(pageable);
	}
	
}
