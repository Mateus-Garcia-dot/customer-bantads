package com.bantads.customer.repository;

import com.bantads.customer.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerModel, String> {
    @Query("SELECT c FROM CustomerModel c WHERE c.cpf = ?1")
    List<CustomerModel> findByCpf(String cpf);

}
