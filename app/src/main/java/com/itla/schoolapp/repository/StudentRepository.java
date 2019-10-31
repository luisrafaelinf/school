package com.itla.schoolapp.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.itla.schoolapp.connection.DbConnection;
import com.itla.schoolapp.entity.Student;

import java.util.ArrayList;
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
		contentValues.put("register_number", student.getRegisterNumber());
		contentValues.put("career_id", student.getCarrerId());

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

		dbConnection.getWritableDatabase()
			.delete(Student.TABLE_NAME, "id = ?", new String[]{entity.getId()+""});

	}

	@Override
	public Student find(Object id) {
		return null;
	}

	@Override
	public List<Student> findAll() {

		List<Student> studentResult = new ArrayList<>();

		Cursor cursor = dbConnection.getReadableDatabase()
			.rawQuery("Select * from " + Student.TABLE_NAME, null);


		while (cursor.moveToNext()) {

			Student student = new Student();
			student.setId(cursor.getInt(cursor.getColumnIndex("id")));
			student.setName(cursor.getString(cursor.getColumnIndex("name")));
			student.setRegisterNumber(cursor.getString(cursor.getColumnIndex("register_number")));
			student.setCareerId(cursor.getInt(cursor.getColumnIndex("career_id")));
			studentResult.add(student);

		}
		cursor.close();

		return studentResult;
	}
}
