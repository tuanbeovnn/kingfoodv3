package com.kingfood.backend.domains.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class CustomerEntity extends BaseEntity {
    private String userName;
    private String address;
    private String city;
    private String phone;
    private String email;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderEntity> orderEntities = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
