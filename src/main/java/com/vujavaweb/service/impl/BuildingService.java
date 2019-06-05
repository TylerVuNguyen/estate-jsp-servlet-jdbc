package com.vujavaweb.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.vujavaweb.converter.BuildingConverter;
import com.vujavaweb.dto.BuildingDTO;
import com.vujavaweb.entity.BuildingEntity;
import com.vujavaweb.paging.PageRequest;
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
		BuildingEntity buildingEntities = buildingRepository.findbyid(buildingDTO.getId());
		buildingEntity.setCreateddate(buildingEntities.getCreateddate());
		buildingEntity.setModifieddate(new Timestamp(System.currentTimeMillis()));
		buildingRepository.update(buildingEntity);
		return buildingDTO;
	}
	@Override
	public BuildingDTO delete(BuildingDTO buildingDTO) {
		BuildingConverter buildingConverter = new BuildingConverter();
		BuildingEntity buildingEntity = buildingConverter.convertToEntiry(buildingDTO);
		buildingEntity.setModifieddate(new Timestamp(System.currentTimeMillis()));
		buildingRepository.delete(buildingEntity.getId());
		return buildingDTO;
	}
	@Override
	public BuildingDTO findById(BuildingDTO buildingDTO) {
		BuildingConverter buildingConverter = new BuildingConverter();
		BuildingEntity buildingEntity = buildingConverter.convertToEntiry(buildingDTO);
		BuildingEntity buildingEntities = buildingRepository.findbyid(buildingEntity.getId());
		BuildingDTO buildingDTO2 = buildingConverter.convertToDTO(buildingEntities);
		return buildingDTO2;
	}
	@Override
	public List<BuildingDTO> findAll(Map<String, Object> properties, PageRequest pageble, Object... where) {
		BuildingConverter buildingConverter = new BuildingConverter();
		List<BuildingEntity> result = buildingRepository.findAll(properties, pageble, where);
		List<BuildingDTO> list = new ArrayList<BuildingDTO>();
		for (int i = 0; i < result.size(); i++) {
			BuildingDTO buildingDTO = buildingConverter.convertToDTO(result.get(i));
			list.add(buildingDTO);
		}
		return list;
	}

}
