package com.wide.test.controller;

import com.wide.test.dto.MasterUserDTO;
import com.wide.test.entity.MasterUser;
import com.wide.test.service.MasterUserService;
import com.wide.test.util.Encrypt;
import com.wide.test.util.GenericResponseDTO;
import com.wide.test.util.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class MasterUserController {

	private MasterUserService userService;

	private JwtTokenUtil tokenUtil;


	@PostMapping("/register")
	public GenericResponseDTO register (@RequestBody MasterUserDTO param){
		GenericResponseDTO responseDTO = new GenericResponseDTO();

		if(param.getUserName()==null || param.getUserName().isEmpty()){
			return responseDTO.errorResponse(400,"User Name Can't Be Empty");
		}

		if(param.getFullName()==null || param.getFullName().isEmpty()){
			return responseDTO.errorResponse(400,"Full Name Can't Be Empty");
		}

		if(param.getAddress()==null || param.getAddress().isEmpty()){
			return responseDTO.errorResponse(400,"Address Can't Be Empty");
		}

		if(param.getPassword()==null || param.getPassword().isEmpty()){
			return responseDTO.errorResponse(400,"Password Name Can't Be Empty");
		}

		try{
			userService.register(param);
		}catch (Exception e){
			return responseDTO.errorResponse(400,e.getMessage());
		}

		return responseDTO.successResponse(tokenUtil.generateToken(param));
	}

	@PostMapping ("/login")
	public GenericResponseDTO login(@RequestBody MasterUserDTO param){
		GenericResponseDTO responseDTO = new GenericResponseDTO();
		Encrypt e = new Encrypt();

		Optional<MasterUser> data = userService.getByUserName(param.getUserName());

		if(data.isPresent()){
			String pwData = e.decrypt(data.get().getPassword());
			if(!param.getPassword().equals(pwData)){
				return responseDTO.errorResponse(400, "Invalid User Name or Password");
			}
		}else {
			return responseDTO.errorResponse(400, "Invalid User Name or Password");
		}

		return responseDTO.successResponse(tokenUtil.generateToken(data.get().toDTO()));


	}
}
