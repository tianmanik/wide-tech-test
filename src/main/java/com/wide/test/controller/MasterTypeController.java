package com.wide.test.controller;

import com.wide.test.dto.MasterTypeDTO;
import com.wide.test.service.MasterTypeService;
import com.wide.test.util.GenericResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/type")
public class MasterTypeController {

	private MasterTypeService typeService;

	@PostMapping("/")
	public GenericResponseDTO save (@RequestBody MasterTypeDTO param){
		GenericResponseDTO responseDTO = new GenericResponseDTO();

		try {
			typeService.save(param);
		}catch (Exception e){
			return responseDTO.errorResponse(400,e.getMessage());
		}
		return responseDTO.successResponse();
	}
}
