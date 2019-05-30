package com.vujavaweb.converter;

import org.modelmapper.ModelMapper;

import com.vujavaweb.dto.BuildingDTO;
import com.vujavaweb.entity.BuildingEntity;

public class BuildingConverter {

public BuildingDTO convertToDTO(BuildingEntity buildingEntity) {
	// khi nào sữ dụng thì mới tạo
	ModelMapper modelMapper = new ModelMapper();
	BuildingDTO result = modelMapper.map(buildingEntity,BuildingDTO.class);
	return result;
	
}
public BuildingEntity convertToEntiry(BuildingDTO buildingDTO) {
	ModelMapper modelMapper = new ModelMapper();
	BuildingEntity result = modelMapper.map(buildingDTO,BuildingEntity.class);
	return result;
}
}
