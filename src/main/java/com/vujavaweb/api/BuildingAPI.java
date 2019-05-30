package com.vujavaweb.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vujavaweb.dto.BuildingDTO;
import com.vujavaweb.service.IBuildingService;
import com.vujavaweb.service.impl.BuildingService;
import com.vujavaweb.utils.HttpUtil;


@WebServlet(urlPatterns = {"/api-admin-building"})
public class BuildingAPI extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5102876994206393527L;
	
	private IBuildingService buildingService;
	public BuildingAPI () {
		buildingService = new BuildingService();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		BuildingDTO buildingDTO = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);		
		// chi cần gọi hàm save trong DAO la xong
		buildingDTO = buildingService.save(buildingDTO);
		// chuyển từ đối tượng thành kiểu dữ liệu json
		objectMapper.writeValue(response.getOutputStream(), buildingDTO);
	}
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		BuildingDTO buildingDTO = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);		
		// chi cần gọi hàm save trong DAO la xong
		buildingDTO = buildingService.update(buildingDTO);
		// chuyển từ đối tượng thành kiểu dữ liệu json
		objectMapper.writeValue(response.getOutputStream(), buildingDTO);
	}
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		BuildingDTO buildingDTO = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);		
		// chi cần gọi hàm save trong DAO la xong
		buildingDTO = buildingService.delete(buildingDTO);
		// chuyển từ đối tượng thành kiểu dữ liệu json
		objectMapper.writeValue(response.getOutputStream(), buildingDTO);
	}
	
	

}
