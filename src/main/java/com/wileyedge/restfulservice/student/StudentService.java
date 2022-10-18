package com.wileyedge.restfulservice.student;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class StudentService {
		
	@Autowired
	@Qualifier(value = "datajpa")
	private StudentJpaRepository dao; 
	
	public StudentService() {
		System.out.println("Default constructor of StudentService");
	}
		
	public Student saveStudent(Student s) {
		System.out.println("Default saveStudent of StudentService");
		return dao.save(s);		
	}
	
	public List<Student> getAllStudents() {
		System.out.println("Inside getAllStudents of StudentService");
		return dao.findAll();
	}
	
	public Student getStudentById(int id) {
		System.out.println("Inside getStudentById of StudentService");
		Optional<Student> s = dao.findById(id);
		if(s.isPresent()) {
			return s.get();
		}
		return null;
	}
	
	public List<Student> getStudentByName(String name) {
		System.out.println("Inside getStudentByName of StudentService");		
		return dao.findByName(name);
	}
	
	@Transactional
	public void removeStudent(int id) {
		System.out.println("Default deleteStudent of StudentService");
		dao.deleteById(id);		
	}

	@Transactional
	public void editStudent(Student s) {
		System.out.println("Default updateStudent of StudentService");
		dao.edit(s.getId(), s.getName(), s.getAge(), s.getMobileNo(), s.getAddress());
	}
}
