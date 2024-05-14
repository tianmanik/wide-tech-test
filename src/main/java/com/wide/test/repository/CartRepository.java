package com.wide.test.repository;

import com.wide.test.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {

	List<Cart> findByUserNameAndIsOrder(String userName, int isOrder);

	Optional<Cart> findByUserNameAndProductCodeAndIsOrder(String userName, String productCode,int isOrder);

	@Modifying
	@Transactional
	@Query(value = "UPDATE Cart  SET isOrder = ?1 WHERE userName = ?2 AND isOrder = ?3")
	int updateCartIsorder (int old ,String userName, int newOrder);
}
