package com.wide.test.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "master_product")
public class MasterProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "master_product_id_seq_generator")
    @SequenceGenerator(name = "master_product_seq_generator", sequenceName = "master_product_id_seq", allocationSize=1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type_code")
    private String typeCode;

    @Column(name = "price")
    private Long price;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "code")
    private String code;


}
