package com.wide.test.dto;

import com.wide.test.entity.Cart;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartResponseDTO {
	List<Cart> cartsData;
	Long total;
}
