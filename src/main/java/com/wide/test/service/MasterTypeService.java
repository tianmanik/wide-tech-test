package com.wide.test.service;

import com.wide.test.dto.MasterTypeDTO;
import com.wide.test.entity.MasterType;
import com.wide.test.repository.MasterTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MasterTypeService {

	private MasterTypeRepository masterTypeRepository;

	public void save (MasterTypeDTO param){
		MasterType data = new MasterType();
		data.setCode(param.getCode());
		data.setName(param.getName());
		masterTypeRepository.save(data);
	}
}
