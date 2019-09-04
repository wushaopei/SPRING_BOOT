package com.webcode.springboot.handlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webcode.springboot.entities.Emp;
import com.webcode.springboot.services.EmpService;

@Controller
public class EmpHandler {
	
	@Autowired
	private EmpService empService;
	
	@ResponseBody
	@RequestMapping("/get/emp/list")
	public List<Emp> getEmpList() {
		return empService.getAll();
	}

	@ResponseBody
	@RequestMapping("/get/hello")
	public String getEmpLssist() {
		return "dfghj";
	}

}
