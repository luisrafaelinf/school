package com.itla.schoolapp.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.itla.schoolapp.connection.DbConnection;
import com.itla.schoolapp.entity.Subject;

import java.util.ArrayList;
import java.util.List;

public final class SubjectRepository implements CrudRepository<Subject> {

	private DbConnection dbConnection;

	public SubjectRepository(Context context) {
		dbConnection = new DbConnection(context);
	}

	@Override
	public long persist(Subject subject) {

		ContentValues contentValues = new ContentValues();
		contentValues.put("description", subject.getDescription());
		contentValues.put("credits", subject.getCredits());

		long id = dbConnection.getWritableDatabase()
			.insert(Subject.TABLE_NAME, null, contentValues);

		if (id < 0) {
			Log.i("Error asignatura", "Error al insertar la asignatura");
		} else {
			Log.i("Registro asignatura", "El registro de la asignatura ha sido exitoso: "+id);
		}

		return id;
	}

	@Override
	public void update(Subject entity) {

	}

	@Override
	public void delete(Subject entity) {

		dbConnection.getWritableDatabase()
			.delete(Subject.TABLE_NAME, "id = ?", new String[]{entity.getId()+""});

	}

	@Override
	public Subject find(Object id) {
		Cursor cursor = dbConnection.getReadableDatabase()
			.rawQuery("select * from " + Subject.TABLE_NAME + " where id = ?", new String[]{String.valueOf(id)});

		Subject subject = new Subject();
		if (cursor.moveToNext()) {
			subject.setId(cursor.getInt(cursor.getColumnIndex("id")));
			subject.setDescription(cursor.getString(cursor.getColumnIndex("description")));
			subject.setCredits(cursor.getInt(cursor.getColumnIndex("credits")));
		}
		cursor.close();

		return subject;
	}

	@Override
	public List<Subject> findAll() {

		List<Subject> careerResult = new ArrayList<>();

		Cursor cursor = dbConnection.getReadableDatabase()
			.rawQuery("Select * from " + Subject.TABLE_NAME, null);

		while (cursor.moveToNext()) {

			Subject subject = new Subject();
			subject.setId(cursor.getInt(cursor.getColumnIndex("id")));
			subject.setDescription(cursor.getString(cursor.getColumnIndex("description")));
			subject.setCredits(cursor.getInt(cursor.getColumnIndex("credits")));
			careerResult.add(subject);

		}
		cursor.close();

		return careerResult;
	}

	public long getTotalCareers(long subjectId) {

		Cursor cursor = dbConnection.getReadableDatabase()
			.rawQuery("select count(*) as total from career_subject where subject_id = ? ", new String[]{String.valueOf(subjectId)});

		long total = 0;
		if (cursor.moveToNext()) {
			total =  cursor.getLong(cursor.getColumnIndex("total"));
		}
		cursor.close();

		return total;
	}

}
