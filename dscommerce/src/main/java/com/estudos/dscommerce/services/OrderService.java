package com.estudos.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estudos.dscommerce.dto.OrderDTO;
import com.estudos.dscommerce.entities.Order;
import com.estudos.dscommerce.repositories.OrderRepository;
import com.estudos.dscommerce.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Transactional(readOnly = true)
	public OrderDTO findbyId(Long id) {
		
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
		
		return new OrderDTO(order);
	}
}
