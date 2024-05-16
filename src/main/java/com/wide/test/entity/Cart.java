package com.wide.test.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "master_cart_id_seq_generator")
    @SequenceGenerator(name = "master_cart_id_seq_generator", sequenceName = "master_cart_id_seq", allocationSize=1)    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "total")
    private Long total;

    @Column(name = "is_order")
    private Long isOrder;

//    @Column(name = "is_paid")
//    private Long isPaid;

}
