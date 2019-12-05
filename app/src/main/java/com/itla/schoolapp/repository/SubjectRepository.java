package com.itla.schoolapp.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.itla.schoolapp.entity.Subject;

import java.util.List;

@Dao
public interface SubjectRepository {

	@Insert
	public long persist(Subject subject);

	@Update
	public void update(Subject entity);

	@Delete
	public void delete(Subject entity);

	@Query("select * from subject where id = :id")
	public Subject find(int id);

	@Query("select * from subject")
	public List<Subject> findAll();


	@Query("select count(*) as total from career_subject where subject_id = :subjectId")
	public long getTotalCareers(long subjectId);

}
