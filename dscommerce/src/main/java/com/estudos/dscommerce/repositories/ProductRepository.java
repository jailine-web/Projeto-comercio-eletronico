package com.estudos.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudos.dscommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
