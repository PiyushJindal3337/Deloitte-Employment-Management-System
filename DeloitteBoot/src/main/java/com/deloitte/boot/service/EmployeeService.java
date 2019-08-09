package com.deloitte.boot.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deloitte.boot.model.Employee;
import com.deloitte.boot.repo.EmployeeRepo;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepo repo;
	
	
	public String generateId(String empName) {
		// TODO Auto-generated method stub
		Random rand=new Random();
		int id = (int)(rand.nextInt(900)+1000);
		String empCode=empName.substring(0,2);
		String empCodeRan= empCode+id;
		//System.out.println("IN generateId in ServiceImp ");
		return empCodeRan;
	}
	public Employee addEmployee(Employee emp)
	{
		System.out.println("In add Emp");
		if(validateEmployee(emp))
		{
			String empId=generateId(emp.getEmpName());
			emp.setEmpId(empId);
			return repo.save(emp);
		}
		return null;
	}
	
	public boolean validateEmployee(Employee emp)
	{
		if(emp.getDob().isAfter(LocalDate.of(1995, 12, 31))|| emp.getDob().isBefore(LocalDate.of(1900, 01, 01)))
			return false;
			
		if( emp.getEmpName().length() < 4 || emp.getEmpName().length()>15 )
			return false;
		
		if(emp.getSal()<20000||emp.getSal()>80000)
			return false;
		
		else
			return true;
	}
	public List<Employee> getAllEmployees()
	{
		return repo.findAll();
	}
	
	public Employee getEmployee(String id)
	{
		return repo.findbyEmpId(id);
	}
	
	public boolean deleteEmployee(String empId)
	{
		Employee emp=repo.findbyEmpId(empId);
		//String msg="";
		if(emp!=null)
		{
		repo.delete(emp);
		return true;
		}
		else
		{
			return false;
		}
	}
	
//	public Employee updateEmployee(Employee emp)
//	{
//		Employee e=repo.findbyEmpId(emp.getEmpId());
//		System.out.println(e);
//		if(e!=null)
//		{
//			emp.setSal(emp.getSal());
//			if(validateEmployee(emp))
//				return repo.save(emp);
//		}
//		return null;
//	}
	
	public Employee updateEmployee(String empId, float sal)
	{
		
		Employee e= repo.findbyEmpId(empId);
		
		if(e!=null && (sal>20000 && sal<80000))
		{
			
			e.setSal(sal);
			return repo.save(e);
		}
		else
		{
			return null;
		}
//		emp.setDob(e.getDob());
		
	}
//	
//	public Employee updateEmployee(Employee emp)
//	{
//		
//		return repo.save(emp);
//	}
	
	

}
