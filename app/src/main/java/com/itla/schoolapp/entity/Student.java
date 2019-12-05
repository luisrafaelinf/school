package com.itla.schoolapp.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "student")
public class Student {

	@PrimaryKey
	private int id;
	@ColumnInfo(name = "career_id")
	private int careerId;
	@ColumnInfo(name = "name")
	private String name;
	@ColumnInfo(name = "register_number")
	private String registerNumber;

	public Student(){}

	public Student(int id) {
		this.id = id;
	}

	public Student(String name, String registerNumber) {
		this.name = name;
		this.registerNumber = registerNumber;
	}

	public Student(int id, String name, String registerNumber) {
		this.id = id;
		this.name = name;
		this.registerNumber = registerNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCarrerId() {
		return careerId;
	}

	public void setCareerId(int id) {
		this.careerId = id;
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
