package com.itla.schoolapp.repository;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.itla.schoolapp.entity.Student;

import java.util.List;

@Dao
public interface StudentRepository {

	@Insert
	public long persist(Student student);

	@Update
	public void update(Student entity);

	@Delete
	public void delete(Student entity);

	@Query("select * from student where id = :id")
	public Student find(int id);

	@Query("Select * from student")
	public List<Student> findAll();
}
