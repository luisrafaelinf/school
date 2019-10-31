package com.itla.schoolapp.connection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public final class DbConnection extends SQLiteOpenHelper {

	private final static int VERSION = 10;
	private final static String NAME_DB = "school.db";

	public DbConnection(@Nullable Context context) {

		super(context, NAME_DB, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("CREATE TABLE \"student\" ( " +
			" \"id\" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
			" \"name\" TEXT NOT NULL, " +
			" \"registerNumber\" TEXT NOT NULL " +
			" );"
		);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println(oldVersion +" --- "+ newVersion);

		if (oldVersion < 10) {
			db.execSQL("drop table \"subject_old\";");
			db.execSQL("alter table \"subject\" rename to \"subject_old\";");
			db.execSQL("CREATE TABLE if not exists \"subject\" (\n" +
				" \"id\" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
				" \"description\" TEXT NOT NULL,\n" +
				" \"credits\" INTEGER NOT NULL \n"+
				");");
		}

		if (oldVersion < 8) {
			db.execSQL("update student set career_id = 1");
		}

		if (oldVersion < 3) {
			db.execSQL("CREATE TABLE \"student\" (\n" +
				" \"id\" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
				" \"name\" TEXT NOT NULL,\n" +
				" \"register_number\" TEXT NOT NULL,\n" +
				" \"career_id\" INTEGER NOT NULL\n" +
				");"
			);
		}

		if (oldVersion < 2) {
			db.execSQL(
				"CREATE TABLE \"career\" (\n" +
					" \"id\" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
					" \"description\" TEXT NOT NULL\n" +
					");"
			);

			db.execSQL(
				"CREATE TABLE \"career_subject\" (\n" +
					" \"career_id\" INTEGER NOT NULL,\n" +
					" \"subject_id\" INTEGER NOT NULL\n" +
					");"
			);

			db.execSQL(
				"CREATE TABLE \"subject\" (\n" +
					" \"id\" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
					" \"description\" TEXT NOT NULL\n" +
					");"
			);

			db.execSQL(
				"alter table \"student\" rename to \"student_old\";"
			);


		}

	}
}
