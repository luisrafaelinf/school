package com.itla.schoolapp.entity;

import java.util.Objects;

public class Career {

	public final static String TABLE_NAME = "career";

	private int id;
	private String description;

	public Career(){}

	public Career(int id) {
		this.id = id;
	}

	public Career(String description) {
		this.description = description;
	}

	public Career(int id, String description) {
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Career)) return false;
		Career career = (Career) o;
		return id == career.id;
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
