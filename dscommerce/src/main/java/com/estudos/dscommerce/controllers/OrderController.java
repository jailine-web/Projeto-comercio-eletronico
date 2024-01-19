package com.estudos.dscommerce.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.estudos.dscommerce.dto.OrderDTO;
import com.estudos.dscommerce.services.OrderService;

import jakarta.validation.Valid;

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
	
	
	@PreAuthorize("hasRole('ROLE_CLIENT' )")
	@PostMapping
	public ResponseEntity<OrderDTO> insertProduct(@Valid @RequestBody OrderDTO order) {
		order = orderService.insertProduct(order);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(order.getId()).toUri();
		return ResponseEntity.created(uri).body(order);
	}
	
}
