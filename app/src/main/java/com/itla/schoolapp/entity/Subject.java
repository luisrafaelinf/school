package com.itla.schoolapp.entity;

import java.util.Objects;

public class Subject {

	public final static String TABLE_NAME = "subject";

	private int id;
	private String description;
	private int credits;

	public Subject(){}

	public Subject(int id) {
		this.id = id;
	}

	public Subject(String description) {
		this.description = description;
	}

	public Subject(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Subject)) return false;
		Subject subject = (Subject) o;
		return id == subject.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	@Override
	public String toString() {
		return description;
	}
}
