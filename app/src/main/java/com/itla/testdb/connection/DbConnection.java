package com.itla.testdb.connection;

import android.content.Context;

import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class DbConnection extends SQLiteOpenHelper {

	private final static int VERSION = 1;
	private final static String NAME_DB = "school.db";

	public DbConnection(@Nullable Context context) {
		super(context, NAME_DB, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("CREATE TABLE \"student\" ( " +
			" \"id\" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
			" \"name\" TEXT NOT NULL, " +
			" \"registerNumber\"\tTEXT NOT NULL " +
			" );"
		);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


	}
}
