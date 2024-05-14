package com.wide.test.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterUserDTO {
    private Long id;
    private String userName;
    private String fullName;
    private String address;
    private String password;

}
