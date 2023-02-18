package com.bantads.customer.consumer;

import com.bantads.customer.model.CustomerModel;
import com.bantads.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
@Data
public class CustomerConsumer {

    private CustomerRepository customerRepository;

    @RabbitListener(queues = "customer.create")
    public void createCustomer(CustomerModel customerModel) {
        this.customerRepository.save(customerModel);
    }

    @RabbitListener(queues = "customer.update")
    public void updateCustomer(CustomerModel customerModel) {
        CustomerModel customer = this.customerRepository.findById(customerModel.getUuid()).orElse(null);
        if (customer == null) {
            return;
        }
        customer.setName(customerModel.getName());
        customer.setCpf(customerModel.getCpf());
        customer.setAddress(customerModel.getAddress());
        customer.setPhone(customerModel.getPhone());
        customer.setSalary(customerModel.getSalary());
    }

    @RabbitListener(queues = "customer.delete")
    public void deleteCustomer(CustomerModel customerModel) {
        this.customerRepository.deleteById(customerModel.getUuid());
    }

    @RabbitListener(queues = "customer.patch")
    public void patchCustomer(CustomerModel customerModel) {
        CustomerModel customer = this.customerRepository.findById(customerModel.getUuid()).orElse(null);
        if (customer == null) {
            return;
        }
        if (customerModel.getName() != null) {
            customer.setName(customerModel.getName());
        }
        if (customerModel.getCpf() != null) {
            customer.setCpf(customerModel.getCpf());
        }
        if (customerModel.getAddress() != null) {
            customer.setAddress(customerModel.getAddress());
        }
        if (customerModel.getPhone() != null) {
            customer.setPhone(customerModel.getPhone());
        }
        if (customerModel.getSalary() != null) {
            customer.setSalary(customerModel.getSalary());
        }
        this.customerRepository.save(customer);
    }
}
