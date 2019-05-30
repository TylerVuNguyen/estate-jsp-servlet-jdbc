package com.vujavaweb.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vujavaweb.dto.UserDTO;
import com.vujavaweb.utils.HttpUtil;

@WebServlet(urlPatterns = { "/api-admin-estate" })
public class UserAPI extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		UserDTO userDTO = HttpUtil.of(request.getReader()).toModel(UserDTO.class);
		System.out.println(userDTO);
		// chi cần gọi hàm save trong DAO la xong
		// chuyển từ đối tượng thành kiểu dữ liệu json
		objectMapper.writeValue(response.getOutputStream(), userDTO);
	}
}