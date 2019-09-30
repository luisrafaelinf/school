package com.itla.testdb.entity;

import java.util.Objects;

public class Student {

	public final static String TABLE_NAME = "student";

	private int id;
	private String name;
	private String registerNumber;

	public Student(int id) {
		this.id = id;
	}

	public Student( String name, String registerNumber) {
		this.name = name;
		this.registerNumber = registerNumber;
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

	public String getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Student)) return false;
		Student student = (Student) o;
		return id == student.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	@Override
	public String toString() {
		return name;
	}
}
