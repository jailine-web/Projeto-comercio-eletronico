package com.estudos.dscommerce.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estudos.dscommerce.dto.CategoryDTO;
import com.estudos.dscommerce.entities.Category;
import com.estudos.dscommerce.repositories.CategoryRepository;

@Service
public class CategoryService {

	private CategoryRepository categoryRepository;
	
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		List<Category> result = categoryRepository.findAll();
		return result.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
	}
	
}