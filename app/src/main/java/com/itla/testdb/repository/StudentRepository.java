package com.itla.testdb.repository;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.itla.testdb.connection.DbConnection;
import com.itla.testdb.entity.Student;

import java.util.List;

public final class StudentRepository implements CrudRepository<Student> {

	private DbConnection dbConnection;

	public StudentRepository(Context context) {
		dbConnection = new DbConnection(context);
	}

	@Override
	public long persist(Student student) {

		ContentValues contentValues = new ContentValues();
		contentValues.put("name", student.getName());
		contentValues.put("registerNumber", student.getRegisterNumber());

		long id = dbConnection.getWritableDatabase()
			.insert(Student.TABLE_NAME, null, contentValues);

		if (id < 0) {
			Log.i("Error estudiante", "Error al insertar el estudiante");
		} else {
			Log.i("Registro estudiante", "El registro del estudiante ha sido exitoso: "+id);
		}

		return id;
	}

	@Override
	public void update(Student entity) {

	}

	@Override
	public void delete(Student entity) {

	}

	@Override
	public Student find(Object id) {
		return null;
	}

	@Override
	public List<Student> findAll() {
		return null;
	}
}
