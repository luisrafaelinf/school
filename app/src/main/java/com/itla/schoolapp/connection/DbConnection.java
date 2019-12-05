package com.itla.schoolapp.connection;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.itla.schoolapp.entity.Career;
import com.itla.schoolapp.entity.CareerSubject;
import com.itla.schoolapp.entity.Student;
import com.itla.schoolapp.entity.Subject;
import com.itla.schoolapp.repository.CareerRepository;
import com.itla.schoolapp.repository.StudentRepository;
import com.itla.schoolapp.repository.SubjectRepository;

import java.util.Objects;

@Database(entities = {CareerSubject.class,Career.class, Student.class, Subject.class}, version = 11, exportSchema = false)
public abstract class DbConnection extends RoomDatabase {

	private final static String NAME_DB = "school.db";
	private static DbConnection db;

	public  static DbConnection getInstance(@Nullable Context context) {

		if (Objects.isNull(db)) {
			db = Room.databaseBuilder(context, DbConnection.class, NAME_DB)
				.build();
		}

		return db;

	}

	public abstract CareerRepository getCareer();
	public abstract StudentRepository getStudent();
	public abstract SubjectRepository getSubjet();
}
