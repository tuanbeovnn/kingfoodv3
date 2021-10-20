package com.kingfood.backend.domains.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders_details")
@Getter
@Setter
public class OrderDetailsEntity extends BaseEntity {

    private double unitPrice;
    private int quantity;
    private double discount;
    private double total;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;



}
