package com.mockproject.du1.services;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockproject.du1.mapper.DepartmentMapper;
import com.mockproject.du1.model.Department;

@Service
public class DepartmentService {

	private static final int STATUS_STAY = 1;
	private static final int STATUS_LEAVE = 0;

	@Autowired
	private DepartmentMapper departmentMapper;

	public List<Department> getAllListDepartment() {

		return departmentMapper.sqlGetAllDepartmentSelect();
	}

	public List<Department> getListDepartmentActive() {

		return departmentMapper.sqlGetDepartmentByStatusSelect(STATUS_STAY);
	}

	public List<Department> getListDepartmentInActive() {

		return departmentMapper.sqlGetDepartmentByStatusSelect(STATUS_LEAVE);
	}

	public Department getDepartmentById(int departmentId) {

		return departmentMapper.sqlGetDepartmentByIdSelect(departmentId);
	}

	public long getMaxDepartmentIdSelect() {

		return departmentMapper.sqlGetMaxDepartmentIdSelect();
	}

	public void sqlDepartmentInfoUpdate(Department department) {

	}

	public void sqlDepartmentStatusUpdate(Department department) {

	}

	public void sqlDepartmentInsert(Department department) {

	}
}
