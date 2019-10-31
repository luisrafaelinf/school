package com.itla.schoolapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.itla.schoolapp.adapter.StudentAdapter;
import com.itla.schoolapp.entity.Career;
import com.itla.schoolapp.entity.Student;
import com.itla.schoolapp.repository.CareerRepository;
import com.itla.schoolapp.repository.StudentRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity {

	private RecyclerView lstStudents;
	private Button btnNewStudent;

	private StudentRepository studentRepository;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();

		studentRepository = new StudentRepository(this.getBaseContext());

		btnNewStudent = findViewById(R.id.btnNewStudent);

		lstStudents = findViewById(R.id.lstStudents);
		LinearLayoutManager llm = new LinearLayoutManager(this.getBaseContext());
		lstStudents.setLayoutManager(llm);

		loadStudents();

		btnNewStudent.setOnClickListener(event -> {
			Intent intent = new Intent(this.getApplicationContext(), StudentForm.class);
			startActivity(intent);
		});


	}

	private void loadStudents() {

		List<Student> students = studentRepository.findAll();
		RecyclerView.Adapter adapterStudents = new StudentAdapter(this.getBaseContext(), students);

		lstStudents.setAdapter(adapterStudents);

	}

	@Override
	protected void onResume() {
		super.onResume();
		loadStudents();
	}
}
