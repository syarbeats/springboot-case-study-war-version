package cdc.mitrais.springboot.casestudy.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;


@Entity
public class Employee implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="id")
	@Id
	private int id;

	@Column(name="name")
	private String name;

	
	@Lob
	@Column(name="photo")
	private byte[] photo;

	@Column(name="salary")
	private int salary;

	@Lob
	@Column(name="working_history")
	private String workingHistory;

	public Employee() {
	}
	
	public Employee(int id, String name, int salary) {
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getPhoto() {
		return this.photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public int getSalary() {
		return this.salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getWorkingHistory() {
		return this.workingHistory;
	}

	public void setWorkingHistory(String workingHistory) {
		this.workingHistory = workingHistory;
	}

}