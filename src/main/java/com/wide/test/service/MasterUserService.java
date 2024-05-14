package com.wide.test.service;

import com.wide.test.dto.MasterUserDTO;
import com.wide.test.entity.MasterUser;
import com.wide.test.repository.MasterUserRepository;
import com.wide.test.util.Encrypt;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MasterUserService {

	private MasterUserRepository userRepository;

	public void register (MasterUserDTO param){
		Encrypt e = new Encrypt();

		MasterUser data = new MasterUser();
		data.setUserName(param.getUserName());
		data.setFullName(param.getFullName());
		data.setAddress(param.getAddress());
		data.setPassword(e.encrypt(param.getPassword()));
		userRepository.save(data);
	}

	public Optional<MasterUser> getByUserName (String userName){
		return userRepository.findByUserName(userName);
	}
}
