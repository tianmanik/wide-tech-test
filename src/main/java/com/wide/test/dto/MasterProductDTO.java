package com.wide.test.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterProductDTO {
    private Long id;
    private String name;
    private String typeCode;
    private Long price;
    private Long quantity;
    private String code;
}
