package com.kingfood.backend.domains.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kingfood.backend.domains.response.ProductProfileResponse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@SqlResultSetMapping(name = "findListOrderByCustomerId", classes =
        {
                @ConstructorResult(targetClass = ProductProfileResponse.class, columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "product_name", type = String.class),
                        @ColumnResult(name = "img_highlight", type = String.class),
                        @ColumnResult(name = "image", type = String.class),
                        @ColumnResult(name = "price", type = Double.class),
                        @ColumnResult(name = "size", type = String.class),
                        @ColumnResult(name = "description", type = String.class),
                        @ColumnResult(name = "nutrition", type = String.class),
                        @ColumnResult(name = "purchases", type = Integer.class),
                })
        }

)
public class ProductEntity extends BaseEntity {

    @Column(name = "product_name")
    private String productName;
    @Column(name = "img_highlight")
    private String imgHighlight;
    @Column(name = "image")
    private String image;
    @Column(name = "price")
    private double price;
    @Column(name = "size")
    private String size;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @Column(name = "nutrition")
    private String nutrition;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "status")
    private String status;

    @Column(name = "discount")
    private Double discount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderDetailsEntity> orderDetailsEntities = new ArrayList<>();


}
