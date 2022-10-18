package com.wileyedge.restfulservice.student;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.persistence.Id;


@Entity(name = "student")
@Table(name = "student")
public class Student {
	
	@Id
	private int id;	
	@Column(name = "name")
	@Size(min=3,message="Name must have atleast 3 chars")
	@NotBlank(message = "Name is mandatory")
	private String name;
	@Column(name = "age")
	private String age;
	@NotBlank
	@Column(name = "mobileNo")
	private String mobileNo;
	@Column(name = "address")
	private String address;
		
	public Student() {
		
	}
	
	public Student(int id, String name, String age, String mobileNo, String address) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.mobileNo = mobileNo;
		this.address = address;
	}
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", mobileNo=" + mobileNo + ", address=" + address
				+ "]";
	}
		
}
