package com.estudos.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudos.dscommerce.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
