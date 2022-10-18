package com.wileyedge.restfulservice.student;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import com.wileyedge.restfulservice.student.Student;

@Repository(value = "inmemory")
public class StudentDao implements IDao {

	private static List<Student> students = new ArrayList<>();
	private static int stdCount = 2;
//	static {
//		students.add(new Student("Elizabeth", "12", "012345678", "Singapore"));
//		students.add(new Student("Albert", "15", "045678910", "Malaysia"));
//	}
	
	public StudentDao() {
		System.out.println("Default constructor of StudentDao");
	}
	
	@Override
	public Student save(Student s) {
		System.out.println("Inside save of StudentDao");
		students.add(s);
		stdCount++;
		return s;
	}

	@Override
	public List<Student> findAll() {
		System.out.println("Inside findAll of StudentDao");
		return students;
	}
	
	@Override
	public Optional<Student> findById(int id) {
		System.out.println("Inside findById of StudentDao");
		Student s = students.stream().filter(a -> a.getId() == id).findAny().orElse(null);
	    Optional<Student> opt = Optional.ofNullable(s);
	    return opt;
	}
	
	@Override
	public List<Student> findByName(String name) {
		System.out.println("Inside findByName of StudentDao");
		Student s = students.stream().filter(a -> a.getName().equals(name)).findAny().orElse(null);
		List<Student> stdlist = new ArrayList<Student>();
		stdlist.add(s);
		return stdlist;
	}
		
	@Override
	public Student deleteById(int id) {
		System.out.println("Inside deleteByName of StudentDao");
		Iterator<Student> iter = students.iterator();
		while(iter.hasNext()) {
			Student s = iter.next();
			if(s.getId()== id) {
				iter.remove();
				return s;
			}
		}
		return null;
	}
	
	@Override
	public void edit(int id, String name, String age, String mobileNo, String address) {
		System.out.println("Inside editById of StudentDao");		
										
	}
}
