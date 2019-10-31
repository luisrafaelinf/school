package com.itla.schoolapp.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.itla.schoolapp.connection.DbConnection;
import com.itla.schoolapp.entity.Career;
import com.itla.schoolapp.entity.Student;

import java.util.ArrayList;
import java.util.List;

public final class CareerRepository implements CrudRepository<Career> {

	private DbConnection dbConnection;

	public CareerRepository(Context context) {
		dbConnection = new DbConnection(context);
	}

	@Override
	public long persist(Career career) {

		ContentValues contentValues = new ContentValues();
		contentValues.put("description", career.getDescription());

		long id = dbConnection.getWritableDatabase()
			.insert(Career.TABLE_NAME, null, contentValues);

		if (id < 0) {
			Log.i("Error carrera", "Error al insertar la carrera");
		} else {
			Log.i("Registro carrera", "El registro de la carrera ha sido exitoso: "+id);
		}

		return id;
	}

	@Override
	public void update(Career entity) {

	}

	@Override
	public void delete(Career entity) {

		dbConnection.getWritableDatabase()
			.delete(Career.TABLE_NAME, "id = ?", new String[]{entity.getId()+""});

	}

	@Override
	public Career find(Object id) {
		Cursor cursor = dbConnection.getReadableDatabase()
			.rawQuery("select * from " + Career.TABLE_NAME + " where id = ?", new String[]{String.valueOf(id)});

		Career career = new Career();
		if (cursor.moveToNext()) {
			career.setId(cursor.getInt(cursor.getColumnIndex("id")));
			career.setDescription(cursor.getString(cursor.getColumnIndex("description")));
		}
		cursor.close();

		return career;
	}

	@Override
	public List<Career> findAll() {

		List<Career> careerResult = new ArrayList<>();

		Cursor careers = dbConnection.getReadableDatabase()
			.rawQuery("Select * from " + Career.TABLE_NAME, null);

		while (careers.moveToNext()) {

			Career career = new Career();
			career.setId(careers.getInt(careers.getColumnIndex("id")));
			career.setDescription(careers.getString(careers.getColumnIndex("description")));
			careerResult.add(career);

		}
		careers.close();
		return careerResult;
	}

	public long getTotalSubjects(long careerId) {

		Cursor cursor = dbConnection.getReadableDatabase()
			.rawQuery("select count(*) as total from career_subject where career_id = ? ", new String[]{String.valueOf(careerId)});

		long total = 0;
		if (cursor.moveToNext()) {
			total = cursor.getLong(cursor.getColumnIndex("total"));
		}
		cursor.close();
		return total;
	}

	public long getTotalCredits(long careerId) {

		Cursor cursor = dbConnection.getReadableDatabase()
			.rawQuery("select sum(s.credits) as total " +
				" from subject s " +
				" inner join career_subject cs on cs.subject_id = s.id " +
				" where career_id = ? ", new String[]{String.valueOf(careerId)});

		long total = 0;
		if (cursor.moveToNext()) {
			total = cursor.getLong(cursor.getColumnIndex("total"));
		}
		cursor.close();

		return total;

	}

	public Boolean insertCareerSubject(int careerId, int subjectId) {
		ContentValues contentValues = new ContentValues();
		contentValues.put("career_id", careerId);
		contentValues.put("subject_id", subjectId);

		long careerSubject = dbConnection.getWritableDatabase()
			.insert("Career_subject", null, contentValues);

		if (careerSubject < 0){
			Log.i("Error carrera asignatura", "Error al insertar la relacion de carrera y asignatura");
			return false;
		} else {
			Log.i("Registro carrera asignatura", "El registro de relacion de carrera y asignatura ha sido exitoso");
			return true;
		}
	}
}
