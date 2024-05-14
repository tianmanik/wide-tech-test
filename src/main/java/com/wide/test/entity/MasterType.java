package com.wide.test.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "master_type")
public class MasterType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "master_type_id_seq_generator")
    @SequenceGenerator(name = "master_type_id_seq_generator", sequenceName = "master_type_id_seq", allocationSize=1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;


}
