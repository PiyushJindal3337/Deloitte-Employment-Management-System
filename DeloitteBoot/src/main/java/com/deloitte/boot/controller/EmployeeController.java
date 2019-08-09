package com.deloitte.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.deloitte.boot.model.Employee;
import com.deloitte.boot.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeService es;
		@GetMapping("/employees")
        public String getAllEmployees( Model m) {
//			Employee emp=es.getEmployee(empId);
			List<Employee> empList=es.getAllEmployees();
			System.out.println(empList);
			m.addAttribute("emp", empList);
			return "viewall.jsp";
		}

        @GetMapping("/employee")
        public String getEmployees(String empId, Model m) {
			Employee emp=es.getEmployee(empId);
			
			if(emp!=null)
			{
				m.addAttribute("emp", emp);
				m.addAttribute("msg", "Employee Details");
				return "show.jsp";
			}
//			List<Employee> empList=es.getEmployee(empId);
//			m.addAttribute("emp", empList);
			else
			{
				m.addAttribute("msg", "No details found");
				return"error.jsp";
		
			}
        }
        @PostMapping ("/employeeAdd")
        public String addEmployee(@ModelAttribute Employee emp,Model m) {
        Employee e=es.addEmployee(emp);
		m.addAttribute("emp", e);
		return "show.jsp";
		}
        	
        	
        @PostMapping ("/delete")
        public String deleteEmployee(@RequestParam("empId")String empId, Model m) 
        {
        	//String msg ="";
        	if(es.deleteEmployee(empId))
        	{	
        		m.addAttribute("emp", "Deletion Successful");
        		return "show.jsp";
        	}
        	else
        	{
        		m.addAttribute("emp","No employee found with such ID");
        		return "error.jsp";
        	}
        }
        
//        @GetMapping("/update")
//        public String updateEmployee(@ModelAttribute Employee emp, Model m)
//        {
//        	es.updateEmployee(emp);
//        	m.addAttribute("emp",emp);
//			return "show.jsp";
//        }
        
        @GetMapping("/update")
        public String updateEmployee(@RequestParam("empId") String empId,@RequestParam("sal") float sal, Model m)
        {
        	Employee emp =es.updateEmployee(empId,sal);
        	m.addAttribute("emp",emp);
        	if(emp!=null)
        	{
        		return"show.jsp";
        	}
        	else
        	{
        		m.addAttribute("msg", "Can not Update");
        		return "error.jsp";
        	}
        }

        
//        @Override
//        @RequestMapping
//        public String getErrorPath()
//        {
//        	return "error.jsp";
//        }


}
