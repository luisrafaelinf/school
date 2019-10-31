package com.itla.schoolapp.repository;

import java.util.List;

public interface CrudRepository<T> {

	long persist(T entity);
	void update(T entity);
	void delete(T entity);
	T find(Object id);
	List<T> findAll();

}
