package com.wide.test.repository;

import com.wide.test.entity.MasterProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MasterProductRepository extends JpaRepository<MasterProduct,Long> {

	List<MasterProduct> findByNameOrTypeCodeAndQuantityNot(String filter, String filter2, int quantity);

	Optional<MasterProduct> findByCode(String code);
}
