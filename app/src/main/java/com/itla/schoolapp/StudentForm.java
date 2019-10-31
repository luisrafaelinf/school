package com.itla.schoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.itla.schoolapp.entity.Career;
import com.itla.schoolapp.entity.Student;
import com.itla.schoolapp.repository.CareerRepository;
import com.itla.schoolapp.repository.StudentRepository;

import java.util.List;

public class StudentForm extends AppCompatActivity {

	private StudentRepository studentRepository;
	private CareerRepository careerRepository;

	private EditText txtNameStudent;
	private EditText txtRegisterNumber;
	private Spinner cmbCareer;
	private Button btnSaveStudent;
	private Button btnAddCareer;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.student_form);
		ActionBar supportActionBar = getSupportActionBar();
		supportActionBar.setTitle("Registro Estudiante");

		txtNameStudent = findViewById(R.id.txtNameStudent);
		txtRegisterNumber = findViewById(R.id.txtRegisterNumber);
		cmbCareer = findViewById(R.id.cmbCareer);
		btnSaveStudent = findViewById(R.id.btnSaveStudent);
		btnAddCareer = findViewById(R.id.btnAddCareer);

		btnSaveStudent.setOnClickListener(this::save);
		btnAddCareer.setOnClickListener(this::addCareer);

		studentRepository = new StudentRepository(this.getBaseContext());
		careerRepository = new CareerRepository(this.getBaseContext());
		loadCareers();

	}

	@Override
	protected void onResume() {
		super.onResume();
		loadCareers();
	}

	private void save(View view) {

		if (!validateFields()) {
			return;
		}

		Student student = new Student();
		student.setName(txtNameStudent.getText().toString().trim());
		student.setRegisterNumber(txtRegisterNumber.getText().toString().trim());
		Career selectedItem = (Career) cmbCareer.getSelectedItem();
		student.setCareerId(selectedItem.getId());

		long id = studentRepository.persist(student);

		if (id > 0) {
			Toast.makeText(this.getBaseContext(), "Estudiante registrado correctamente!", Toast.LENGTH_LONG)
			.show();
			clearComponents();
		}

	}

	private boolean validateFields() {

		if (txtNameStudent.getText().toString().trim().isEmpty()) {
			txtNameStudent.setError("Especifique el nombre del estudiante.");
			return false;
		}

		if (txtRegisterNumber.getText().toString().trim().isEmpty()) {
			txtRegisterNumber.setError("Especifique el número de registro del estudiante.");
			return false;
		}

		return true;
	}

	private void clearComponents() {
		txtRegisterNumber.setText("");
		txtNameStudent.setText("");
	}

	private void loadCareers() {

		List<Career> careers = careerRepository.findAll();
		careers.sort((c1, c2) -> c1.getDescription().compareToIgnoreCase(c2.getDescription()));

		ArrayAdapter array = new ArrayAdapter(this.getBaseContext(), R.layout.support_simple_spinner_dropdown_item, careers);

		cmbCareer.setAdapter(array);

	}

	private void addCareer(View view) {

		Intent intent = new Intent(this.getBaseContext(), CareerMainActivity.class);
		startActivity(intent);
	}


}
