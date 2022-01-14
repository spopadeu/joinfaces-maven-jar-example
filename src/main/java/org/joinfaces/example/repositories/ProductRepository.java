package org.joinfaces.example.repositories;

import org.joinfaces.example.entities.Employee;
import org.joinfaces.example.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


}