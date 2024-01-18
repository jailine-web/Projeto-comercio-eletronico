package com.estudos.dscommerce.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.dscommerce.dto.OrderDTO;
import com.estudos.dscommerce.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

	private OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<OrderDTO> findbyId(@PathVariable Long id) {
		 
		OrderDTO dto = orderService.findbyId(id);
		return ResponseEntity.ok(dto);
	}
	
}
