package com.bantads.customer.controller;

import com.bantads.customer.model.CustomerModel;
import com.bantads.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerRepository customerRepository;

    @GetMapping
    public ResponseEntity<List<CustomerModel>> getAllCustomer() {
        List<CustomerModel> customerModelList = this.customerRepository.findAll();
        return ResponseEntity.ok(customerModelList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerModel> getCustomerById(@PathVariable String id) {
        CustomerModel customerModel = this.customerRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(customerModel);
    }

    @PostMapping
    public ResponseEntity<CustomerModel> createCustomer(@RequestBody CustomerModel customerModel) {
        CustomerModel customerCustomerModel = this.customerRepository.save(customerModel);
        return ResponseEntity.ok(customerCustomerModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerModel> updateCustomer(@PathVariable String id, @RequestBody CustomerModel customerModel) {
        CustomerModel customer = this.customerRepository.findById(id).orElseThrow();
        customer.setName(customerModel.getName());
        customer.setCpf(customerModel.getCpf());
        customer.setAddress(customerModel.getAddress());
        customer.setPhone(customerModel.getPhone());
        customer.setSalary(customerModel.getSalary());
        return ResponseEntity.ok(this.customerRepository.save(customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String id) {
        this.customerRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
}
