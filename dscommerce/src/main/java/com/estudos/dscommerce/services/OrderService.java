package com.estudos.dscommerce.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estudos.dscommerce.dto.OrderDTO;
import com.estudos.dscommerce.dto.OrderItemDTO;
import com.estudos.dscommerce.entities.Order;
import com.estudos.dscommerce.entities.OrderItem;
import com.estudos.dscommerce.entities.Product;
import com.estudos.dscommerce.entities.User;
import com.estudos.dscommerce.repositories.OrderItemRepository;
import com.estudos.dscommerce.repositories.OrderRepository;
import com.estudos.dscommerce.repositories.ProductRepository;
import com.estudos.dscommerce.services.exceptions.ResourceNotFoundException;
import com.estudos.dscommerce.utils.OrderStatus;

import jakarta.validation.Valid;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthService authService;
	
	@Transactional(readOnly = true)
	public OrderDTO findbyId(Long id) {
		
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
		authService.validateSelfOrAdmin(order.getClient().getId());
		
		return new OrderDTO(order);
	}

	@Transactional
	public @Valid OrderDTO insertProduct(OrderDTO dto) {
		
		Order order = new Order();
		order.setMoment(Instant.now());
		order.setStatus(OrderStatus.WAITING_PAYMENT);
		
		User user = userService.authenticated();
		order.setClient(user);
		
		for(OrderItemDTO itemDto: dto.getItems()) {
			
			Product product = productRepository.getReferenceById(itemDto.getProductId());
			OrderItem orderItem = new OrderItem(order, product, itemDto.getQuantity(), 
					product.getPrice());
			order.getItems().add(orderItem);
		}
		
		orderRepository.save(order);
		orderItemRepository.saveAll(order.getItems());
		
		return new OrderDTO(order);
	}
}
