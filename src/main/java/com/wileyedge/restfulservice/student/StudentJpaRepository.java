package com.wileyedge.restfulservice.student;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository(value = "datajpa")
//@Profile("dev")
public interface StudentJpaRepository extends IDao, JpaRepository<Student, Integer> {
	
	public List<Student> findByName(String name);
	@Transactional
	@Modifying
	@Query("UPDATE student s SET s.name = ?2, s.age = ?3, s.mobileNo = ?4, s.address = ?5 WHERE s.id = ?1")
	public void edit(int id, String name, String age, String mobileNo, String address);
	/*
	 findById : Retrieves one object based on the ID you pass it
	 findAll  :	Retrieves a list of all the objects of the type
		save  :	Used for both creating and updating an object; returns the object that was created or updated as it now exists in the database
	 deleteById : Deletes the object with the passed ID from the database
	 count : Retrieves a count of all objects of a type in the database
	 existsById : Checks if an object with the passed ID exists in the database; returns true or false
	 */
	
	/*
	The Java Standard Library class Optional. JPA uses Optional<> as the return type for 
	any built-in method that returns just one object, like findById. Optional exists to avoid 
	returning null; instead, an Optional is returned that may or may not contain an object. 
	You can then check the Optional to see if it has an object and pull it out.
	
	JPA uses query methods to handle anything past simple CRUD operations. The idea is that you 
 	create interface method signatures in a specific format that the system can interpret and 
 	convert to SQL queries behind the scenes.
 	
 	These method names always start with findBy, followed by the filters we want to apply 
 	to the query eg List<Store> findByManager(String manager); internally is converted to
    SELECT * FROM store WHERE manager = ? 
 	The question mark will be filled in by the parameter of the method.
 	
 	We can include more than one field in the filter as
 	Store findByManagerAndLocation(String manager, String location);
 	separate the two filed names with "And". This also works for "Or" and a number of other SQL keywords
 	
 	https://docs.spring.io/spring-data/jpa/docs/1.4.2.RELEASE/reference/html/jpa.repositories.html#jpa.query-methods
	*/
}
