package com.wide.test.repository;

import com.wide.test.entity.MasterUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface MasterUserRepository extends JpaRepository<MasterUser,Long> {

	Optional<MasterUser> findByUserName(String userName);

}
