package com.itla.schoolapp.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.itla.schoolapp.entity.Career;

import java.util.List;

@Dao
public interface CareerRepository {

	@Insert
	public long persist(Career career);

	@Update
	public void update(Career entity);

	@Delete
	public void delete(Career entity);

	@Query("select * from career where id = :id")
	public Career find(Integer id);

	@Query("select * from career")
	public List<Career> findAll();

	@Query("select count(*) as total from career_subject where career_id = :careerId")
	public long getTotalSubjects(long careerId);

	@Query("select sum(s.credits) as total  from subject s inner join career_subject cs on cs.subject_id = s.id  where career_id = :careerId")
	public long getTotalCredits(long careerId);

	@Query("insert into career_subject(career_id, subject_id) values(:careerId, :subjectId)")
	public long insertCareerSubject(int careerId, int subjectId);
}
