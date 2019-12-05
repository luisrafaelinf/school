package com.itla.schoolapp.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "career")
public class Career {

	@PrimaryKey
	private int id;
	@ColumnInfo(name = "description")
	private String description;

	public Career(){}

	@Ignore
	public Career(int id) {
		this.id = id;
	}

	@Ignore
	public Career(String description) {
		this.description = description;
	}

	@Ignore
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
