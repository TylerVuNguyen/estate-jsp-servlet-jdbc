package com.vujavaweb.converter;

import org.modelmapper.ModelMapper;

import com.vujavaweb.dto.UserDTO;
import com.vujavaweb.entity.UserEntity;

public class UserConverter {
public UserDTO convertToDTO(UserEntity userEntity) {
	ModelMapper modelMapper = new ModelMapper();
	UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
	return userDTO;
}
public UserEntity convertToEntity(UserDTO UserDTO) {
	ModelMapper modelMapper = new ModelMapper();
	UserEntity userEntity = modelMapper.map(UserDTO, UserEntity.class);
	return userEntity;
}
}
