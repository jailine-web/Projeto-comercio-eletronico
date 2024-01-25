package com.estudos.dscommerce;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.estudos.dscommerce.entities.Order;
import com.estudos.dscommerce.repositories.OrderRepository;
import com.estudos.dscommerce.utils.OrderStatus;

@SpringBootApplication
public class DscommerceApplication implements CommandLineRunner{
	
	@Autowired
	private OrderRepository orderRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DscommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Order order = new Order(null, Instant.now(), OrderStatus.SHIPPED, null, null);
		orderRepository.save(order);
		
	}

	

}
