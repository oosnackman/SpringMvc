package com.mark.crud.dao;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mark.crud.entities.Department;
import com.mark.crud.entities.Employee;



@Repository
public class EmployeeDao {

	private static Map<Integer, Employee> employees = null;
	
	@Autowired
	private DepartmentDao departmentDao;
	
	static{
		employees = new HashMap<Integer, Employee>();

		employees.put(1001, new Employee(1001, "馬英九", "aa@163.com", 1, new Department(101, "資訊部"), new Date( ),(float) 50000));
		employees.put(1002, new Employee(1002, "陳水扁", "bb@163.com", 1, new Department(102, "人事部"), new Date( ),(float) 80000));
		employees.put(1003, new Employee(1003, "韓國瑜", "cc@163.com", 1, new Department(103, "採購部"), new Date( ),(float) 60000));
		employees.put(1004, new Employee(1004, "柯文哲", "dd@163.com", 1, new Department(104, "稽查部"), new Date( ),(float) 120000));
		employees.put(1005, new Employee(1005, "學姊", "ee@163.com", 0, new Department(105, "品保部"), new Date( ),(float) 70000));
	}
	
	private static Integer initId = 1006;
	
	public void save(Employee employee){
		if(employee.getId() == null){
			employee.setId(initId++);
		}
		
		employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));
		employees.put(employee.getId(), employee);
	}
	
	public Collection<Employee> getAll(){
		return employees.values();
	}
	
	public Employee get(Integer id){
		return employees.get(id);
	}
	
	public void delete(Integer id){
		employees.remove(id);
	}
}
