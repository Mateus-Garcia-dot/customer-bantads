package com.bantads.customer.consumer;

import com.bantads.customer.model.CustomerModel;
import com.bantads.customer.repository.CustomerRepository;
import lombok.Data;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@Data
public class CustomerConsumer {

    private final CustomerRepository customerRepository;

    @RabbitListener(queues = "customer.create")
    public void createCustomer(CustomerModel customerModel) {
        CustomerModel customerCustomerModel = this.customerRepository.save(customerModel);
    }

    @RabbitListener(queues = "customer.update")
    public void updateCustomer(CustomerModel customerModel) {
        CustomerModel customer = this.customerRepository.findById(customerModel.getId()).orElse(null);
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
        this.customerRepository.deleteById(customerModel.getId());
    }
}
