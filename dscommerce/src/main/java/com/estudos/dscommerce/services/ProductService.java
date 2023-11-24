package com.estudos.dscommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estudos.dscommerce.dto.ProductDTO;
import com.estudos.dscommerce.entities.Product;
import com.estudos.dscommerce.repositories.ProductRepository;

@Service
public class ProductService {

	private ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Transactional(readOnly = true)
	public ProductDTO findbyId(Long id) {
		Optional<Product> result = productRepository.findById(id);
		Product product = result.get();
		ProductDTO dto = new ProductDTO(product);
		
		return dto;
	}
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(Pageable pageable) {
		Page<Product> product = productRepository.findAll(pageable);
		return product.map(x -> new ProductDTO(x));
	}
}
