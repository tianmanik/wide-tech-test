package com.wide.test.service;

import com.wide.test.dto.CartDTO;
import com.wide.test.entity.Cart;
import com.wide.test.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartService {

	private CartRepository cartRepository;

	private MasterProductService productService;

	public void addCart (List<CartDTO> param, String userName){


		List<Cart> carts = new ArrayList<>();

		for(CartDTO x : param){

			Optional<Cart> checkCart = cartRepository.findByUserNameAndProductCodeAndIsOrder(userName,x.getProductCode(),0);
			Long price = productService.findByCode(x.getProductCode()).get().getPrice();


			if(checkCart.isPresent()){
				Cart presentData = checkCart.get();
				presentData.setQuantity(presentData.getQuantity()+x.getQuantity());
				presentData.setTotal(presentData.getTotal()+(x.getQuantity()*price));
				carts.add(presentData);
			}else {
				Cart data = new Cart();
				data.setIsOrder(0L);
				data.setProductCode(x.getProductCode());
				data.setQuantity(x.getQuantity());
				data.setTotal(x.getQuantity()*price);
				data.setUserName(userName);
				carts.add(data);
			}


		}

		cartRepository.saveAll(carts);
	}

	public List<Cart> findByUserName(String userName){
		return cartRepository.findByUserNameAndIsOrder(userName,0);
	}


	@Transactional
	public void placeOrder (String userName){
		cartRepository.updateCartIsorder(1,userName,0);

	}


}
