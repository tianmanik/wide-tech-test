package com.wide.test.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDTO {
    private Long id;
    private String userName;
    private String productCode;
    private Long quantity;
    private Long total;
    private Long isOrder;
//    private Long isPaid;


}
