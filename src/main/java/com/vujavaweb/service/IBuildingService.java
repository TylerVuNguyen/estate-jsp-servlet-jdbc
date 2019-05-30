package com.vujavaweb.service;

import com.vujavaweb.dto.BuildingDTO;

public interface IBuildingService {
	BuildingDTO save(BuildingDTO buildingDTO);
	BuildingDTO update(BuildingDTO buildingDTO);
	BuildingDTO delete(BuildingDTO buildingDTO);
}
