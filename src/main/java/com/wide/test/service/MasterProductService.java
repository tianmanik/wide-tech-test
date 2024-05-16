package com.wide.test.service;

import com.wide.test.dto.MasterProductDTO;
import com.wide.test.entity.MasterProduct;
import com.wide.test.repository.MasterProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MasterProductService {

	private MasterProductRepository productRepository;


	public List<MasterProduct> findByFilter (String filter){
		return productRepository.findByNameOrTypeCodeAndQuantityNot(filter,filter,0);
	}

	public Optional<MasterProduct> findByCode(String code){
		return productRepository.findByCode(code);
	}

	public void save (MasterProductDTO param){
		MasterProduct data = new MasterProduct();
		data.setCode(param.getCode());
		data.setName(param.getName());
		data.setPrice(param.getPrice());
		data.setQuantity(param.getQuantity());
		data.setTypeCode(param.getTypeCode());

		productRepository.save(data);
	}

	public void updateQuantity (MasterProduct param, Long quantity){
		param.setQuantity(param.getQuantity()-quantity);
		productRepository.save(param);
	}

	public void saveAll(List<MasterProduct> datas){
		productRepository.saveAll(datas);
	}


}
