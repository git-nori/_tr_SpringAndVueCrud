package com.example.dictionary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dictionary.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByAge(int age); // ageをキーに検索するメソッド
}
