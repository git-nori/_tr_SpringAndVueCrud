package com.example.dictionary.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dictionary.model.Customer;
import com.example.dictionary.repository.CustomerRepository;

@CrossOrigin(origins = "http://localhost:8081") // frontendからのアクセスの際にCORSを防ぐ
@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerRepository repository;

    // READ(ALL)
    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        List<Customer> customers = repository.findAll();

        return customers;
    }

    // READ
    @GetMapping("/customers/age/{age:.}")
    public List<Customer> getCustomersByAge(@PathVariable("age") int age) {
        List<Customer> customers = repository.findByAge(age);

        return customers;
    }

    // CREATE
    @PostMapping("/customer")
    public Customer postCustomer(@RequestBody Customer customer) {
        Customer _customer = repository.save(new Customer(customer.getName(), customer.getAge()));

        System.out.println(_customer);
        return _customer;
    }

    // UPDATE
    @PutMapping("/customer/{id:.}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {
        Optional<Customer> customerData = repository.findById(id); // Optional => nullも許容する(isPresentで存在チェック可能)

        if (!customerData.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Customer _customer = customerData.get();
            _customer.setName(customer.getName());
            _customer.setAge(customer.getAge());
            _customer.setActive(customer.isActive());
            return new ResponseEntity<>(repository.save(_customer), HttpStatus.OK);
        }
    }

    // DELETE
    @DeleteMapping("/customer/{id:.}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") long id){
        repository.deleteById(id);

        return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
    }
}
