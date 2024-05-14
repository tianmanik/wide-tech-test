package com.wide.test.entity;

import com.wide.test.dto.MasterUserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "master_user")
public class MasterUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "master_user_id_seq_generator")
    @SequenceGenerator(name = "master_user_id_seq_generator", sequenceName = "master_user_id_seq", allocationSize=1)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "address")
    private String address;

    @Column(name = "password")
    private String password;


    public MasterUserDTO toDTO(){
        MasterUserDTO x = new MasterUserDTO();
        x.setUserName(this.userName);
        x.setFullName(this.fullName);
        x.setAddress(this.address);
        return x;
    }
}
