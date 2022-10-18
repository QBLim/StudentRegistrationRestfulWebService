package com.wileyedge.restfulservice.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;


@RestController
@CrossOrigin
public class StudentRegistry {
	@Autowired
	private StudentService service;
	
	public StudentRegistry() {
		System.out.println("Inside default constructor of StudentRegistry");
	}
	
//	@RequestMapping(method = RequestMethod.POST, value = "/student")
	@PostMapping(path = "/student")
	public Student newStudent(@RequestBody Student s, BindingResult result, Model model) {
		System.out.println("Inside saveStudent of StudentRegistry");
		if(result.hasErrors()) {
			System.out.println("newStudent failed to save");
		} else {
			System.out.println("newStudent is saved");
		}
		Student s1 = service.saveStudent(s);
		System.out.println("newStudent is saved: " + s1);
		return s1;
	}

//	@RequestMapping(method = RequestMethod.GET, value = "/student")
	@GetMapping(path = "/student")
	public List<Student> retrieveAllStudent(){
		System.out.println("Inside retrieveAllStudent of StudentRegistry");
		List<Student> stdlist = service.getAllStudents();
		System.out.println(stdlist);
		return stdlist;	
	}
		
//	@RequestMapping(method = RequestMethod.GET, value = "/student/{name}")
	@GetMapping(path = "/student/{id}")
	public Student retrieveStudentId(@PathVariable int id) {
		System.out.println("Inside retrieveStudentId of StudentRegistry: " + id);
		Student s = service.getStudentById(id);	
		if(s == null) {
			System.out.println("Custom exception is thrown");
			throw new UserNotFoundException(id + " not found");
		}
		return s;
	}			
	
//	@RequestMapping(method = RequestMethod.GET, value = "/student/{name}")
	@GetMapping(path = "/student/name/{name}")
	public List<Student> retrieveStudentName(@PathVariable String name) {
		System.out.println("Inside retrieveStudentName of StudentRegistry: " + name);
		Student s = new Student();
		s.setName(name);
		List<Student> stdlist = service.getStudentByName(s.getName());		
		if(stdlist == null) {
			return null;
//			System.out.println("Custom exception is thrown");
//			throw new UserNotFoundException(s.getName() + " not found");			
		}
		return stdlist;	
	}	

	@DeleteMapping(path = "/student/{id}")
	public void deleteStudent(@PathVariable int id) {
		System.out.println("Inside deleteUser of StudentRegistry");
		service.removeStudent(id);
		System.out.println("Student is deleted: " + id);
	}
		
	@PutMapping(path = "/student/{id}")
	public void updateStudent(@RequestBody Student s, @PathVariable int id , BindingResult result, Model model) {
		System.out.println("Inside updateStudent of StudentRegistry");
		if(result.hasErrors()) {
			System.out.println("updateStudent failed to save " + s);
		} else {
			System.out.println("updateStudent is saved " + s);
		}
		service.editStudent(s);
	}
	
}