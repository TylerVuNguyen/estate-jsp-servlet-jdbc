package com.vujavaweb.service.impl;

import java.util.List;
import java.sql.Timestamp;

import com.vujavaweb.converter.BuildingConverter;
import com.vujavaweb.dto.BuildingDTO;
import com.vujavaweb.entity.BuildingEntity;
import com.vujavaweb.repository.IBuildingRepository;
import com.vujavaweb.repository.impl.BuildingRepository;
import com.vujavaweb.service.IBuildingService;

public class BuildingService implements IBuildingService {
	private IBuildingRepository buildingRepository;
	public BuildingService() {
		buildingRepository = new BuildingRepository();
	}
	@Override
	public BuildingDTO save(BuildingDTO buildingDTO) {
		BuildingConverter buildingConverter = new BuildingConverter();
		BuildingEntity buildingEntity = buildingConverter.convertToEntiry(buildingDTO);
		buildingEntity.setCreateddate(new Timestamp(System.currentTimeMillis()));
		buildingRepository.insert(buildingEntity);
		return buildingDTO;
	}
	@Override
	public BuildingDTO update(BuildingDTO buildingDTO) {
		BuildingConverter buildingConverter = new BuildingConverter();
		BuildingEntity buildingEntity = buildingConverter.convertToEntiry(buildingDTO);
		List<BuildingEntity> buildingEntities = buildingRepository.findbyid(buildingDTO.getId());
		buildingEntity.setCreateddate(buildingEntities.get(0).getCreateddate());
		buildingEntity.setModifieddate(new Timestamp(System.currentTimeMillis()));
		buildingRepository.update(buildingEntity);
		return buildingDTO;
	}
	@Override
	public BuildingDTO delete(BuildingDTO buildingDTO) {
		BuildingConverter buildingConverter = new BuildingConverter();
		BuildingEntity buildingEntity = buildingConverter.convertToEntiry(buildingDTO);
		buildingEntity.setModifieddate(new Timestamp(System.currentTimeMillis()));
		buildingRepository.delete(buildingEntity);
		return buildingDTO;
	}

}
