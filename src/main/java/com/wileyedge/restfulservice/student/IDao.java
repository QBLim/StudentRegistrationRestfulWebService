package com.wileyedge.restfulservice.student;

import java.util.List;
import java.util.Optional;

public interface IDao {

	public Student save(Student s);
	public List<Student> findAll();
	Optional<Student> findById(int id);
	public List<Student> findByName(String name);
	public Student deleteById(int id);
	public void edit(int id, String name, String age, String mobileNo, String address);
}
