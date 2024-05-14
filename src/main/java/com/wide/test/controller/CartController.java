package com.wide.test.controller;

import com.wide.test.dto.CartDTO;
import com.wide.test.service.CartService;
import com.wide.test.util.GenericResponseDTO;
import com.wide.test.util.JwtTokenUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {

	private CartService cartService;
	private JwtTokenUtil tokenUtil;

	@PostMapping("/")
	public GenericResponseDTO addCart (@RequestBody List<CartDTO> param){
		GenericResponseDTO responseDTO = new GenericResponseDTO();

		try {
			cartService.addCart(param,"test");
		}catch (Exception e){
			return responseDTO.errorResponse(400,e.getMessage());
		}

		return responseDTO.successResponse();
	}

	@GetMapping("/user")
	public GenericResponseDTO getByUser (@RequestHeader("Authorization") String authorizationHeader){
		String userName = tokenUtil.getUserNameFromToken(authorizationHeader.split(" ")[1]);
		GenericResponseDTO responseDTO = new GenericResponseDTO();
		return responseDTO.successResponse(cartService.findByUserName(userName));
	}

	@PostMapping("/order")
	public  GenericResponseDTO placeOrder (@RequestHeader("Authorization") String authorizationHeader){
		String userName = tokenUtil.getUserNameFromToken(authorizationHeader.split(" ")[1]);
		GenericResponseDTO responseDTO = new GenericResponseDTO();

		try {
			cartService.placeOrder(userName);
		}catch (Exception e){
			return responseDTO.errorResponse(400,e.getMessage());
		}

		return responseDTO.successResponse();

	}
}
