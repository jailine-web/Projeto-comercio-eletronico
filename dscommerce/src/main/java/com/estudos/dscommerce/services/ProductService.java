package com.estudos.dscommerce.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.estudos.dscommerce.dto.ProductDTO;
import com.estudos.dscommerce.entities.Product;
import com.estudos.dscommerce.repositories.ProductRepository;
import com.estudos.dscommerce.services.exceptions.DatabaseException;
import com.estudos.dscommerce.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

	private ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Transactional(readOnly = true)
	public ProductDTO findbyId(Long id) {
		
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
		ProductDTO dto = new ProductDTO(product);
		
		return dto;
	}
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(Pageable pageable) {
		Page<Product> product = productRepository.findAll(pageable);
		return product.map(x -> new ProductDTO(x));
	}
	
	@Transactional
	public ProductDTO insertProduct(ProductDTO product) {

		Product entity = new Product();
		copyDtoToEntity(entity, product);
		entity = productRepository.save(entity);
		
		return new ProductDTO(entity);
	}
	
	@Transactional
	public ProductDTO updateProduct(Long id, ProductDTO product) {
		
		try {
			
			Product entity = productRepository.getReferenceById(id);
			copyDtoToEntity(entity, product);
			entity = productRepository.save(entity);
			return new ProductDTO(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		
	}

	private void copyDtoToEntity(Product entity, ProductDTO product) {
		
		entity.setName(product.getName());
		entity.setDescription(product.getDescription());
		entity.setPrice(product.getPrice());
		entity.setImgUrl(product.getImgUrl());
	}
	
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void deleteProduct(Long id) {
		
		if(!productRepository.existsById(id)) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		try {
			
			productRepository.deleteById(id);
		}
		catch(DataIntegrityViolationException d) {
			throw new DatabaseException("Falha de integridade referencial");
		}
		
	}
	
}
