package com.wide.test.service;

import com.wide.test.dto.CartDTO;
import com.wide.test.dto.CartResponseDTO;
import com.wide.test.entity.Cart;
import com.wide.test.entity.MasterProduct;
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

	public CartResponseDTO getCart(String userName){
		List<Cart> datas = this.findByUserName(userName);
		CartResponseDTO result = new CartResponseDTO();
		result.setCartsData(datas);
		result.setTotal(0L);
		for(Cart x :datas){
			result.setTotal(result.getTotal()+x.getTotal());
		}

		return result;
	}


	@Transactional
	public void placeOrder (String userName){
		List<Cart> dataCarts = findByUserName(userName);
		List<MasterProduct> dataProducts = new ArrayList<>();
		for(Cart x : dataCarts){
			MasterProduct dataProduct = productService.findByCode(x.getProductCode()).get();
			dataProduct.setQuantity(dataProduct.getQuantity()-x.getQuantity());
			dataProducts.add(dataProduct);
		}
		productService.saveAll(dataProducts);

		cartRepository.updateCartIsorder(1,userName,0);

	}


}
