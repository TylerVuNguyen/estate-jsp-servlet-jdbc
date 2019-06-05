package com.vujavaweb.service;

import java.util.List;
import java.util.Map;

import com.vujavaweb.dto.BuildingDTO;
import com.vujavaweb.paging.PageRequest;

public interface IBuildingService {
	BuildingDTO save(BuildingDTO buildingDTO);
	BuildingDTO update(BuildingDTO buildingDTO);
	BuildingDTO delete(BuildingDTO buildingDTO);
	BuildingDTO findById(BuildingDTO buildingDTO);
	List<BuildingDTO> findAll(Map<String, Object> properties,PageRequest  pageble,Object... where);
}
