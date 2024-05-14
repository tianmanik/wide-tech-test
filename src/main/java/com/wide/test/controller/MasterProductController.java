package com.wide.test.controller;

import com.wide.test.service.MasterProductService;
import com.wide.test.util.GenericResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class MasterProductController {

	private MasterProductService masterProductService;

	@GetMapping("/tes")
	public GenericResponseDTO getByFilter(@RequestParam ("filter") String filter){
		GenericResponseDTO responseDTO = new GenericResponseDTO();
		return responseDTO.successResponse(masterProductService.findByFilter(filter));
	}
}
