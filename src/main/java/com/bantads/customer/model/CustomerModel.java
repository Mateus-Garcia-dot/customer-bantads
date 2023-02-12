package com.bantads.customer.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerModel {
    @Id
    private String uuid = java.util.UUID.randomUUID().toString();
    private String name;
    private String cpf;
    private Long address;
    private String phone;
    private Double salary;
}
