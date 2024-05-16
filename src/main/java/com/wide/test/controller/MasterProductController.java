package com.wide.test.controller;

import com.wide.test.dto.MasterProductDTO;
import com.wide.test.service.MasterProductService;
import com.wide.test.util.GenericResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class MasterProductController {

	private MasterProductService masterProductService;

	@PostMapping("/")
	public GenericResponseDTO save (@RequestBody MasterProductDTO param){
		GenericResponseDTO responseDTO = new GenericResponseDTO();
		try {
			masterProductService.save(param);
		}catch (Exception e){
			return responseDTO.errorResponse(400,e.getMessage());
		}

		return responseDTO.successResponse();
	}

	@GetMapping("/tes")
	public GenericResponseDTO getByFilter(@RequestParam ("filter") String filter){
		GenericResponseDTO responseDTO = new GenericResponseDTO();
		return responseDTO.successResponse(masterProductService.findByFilter(filter));
	}
}

