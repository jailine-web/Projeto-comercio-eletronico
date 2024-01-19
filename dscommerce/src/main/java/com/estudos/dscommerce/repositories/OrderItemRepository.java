package com.estudos.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudos.dscommerce.entities.OrderItem;
import com.estudos.dscommerce.entities.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK>{

}
