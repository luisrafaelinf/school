package com.itla.testdb;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.itla.testdb.entity.Student;
import com.itla.testdb.repository.StudentRepository;

public class MainActivity extends AppCompatActivity {

	StudentRepository studentRepository;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Student student1 = new Student("Luis", "123");

		studentRepository = new StudentRepository(this.getBaseContext());

		studentRepository.persist(student1);

		Student student2 = new Student("Rafael", "123");
		studentRepository.persist(student2);

	}
}
