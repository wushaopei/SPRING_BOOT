package com.webcode.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webcode.springboot.entities.Emp;
import com.webcode.springboot.mappers.EmpMapper;

@Service
@Transactional(readOnly=true)
public class EmpServiceImpl implements EmpService {
	
	@Autowired
	private EmpMapper empMapper;

	public List<Emp> getAll() {
		return empMapper.selectAll();
	}

}
