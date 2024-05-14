package com.wide.test.service;

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

}
