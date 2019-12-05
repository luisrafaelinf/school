package com.itla.schoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itla.schoolapp.adapter.SubjectAdapter;
import com.itla.schoolapp.connection.DbConnection;
import com.itla.schoolapp.entity.Career;
import com.itla.schoolapp.entity.Subject;
import com.itla.schoolapp.repository.CareerRepository;
import com.itla.schoolapp.repository.SubjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CareerForm extends AppCompatActivity {

	private List<Subject> subjects = new ArrayList<>();
	private SubjectAdapter subjectAdapter;

	private CareerRepository careerRepository;
	private SubjectRepository subjectRepository;
	private EditText txtDescriptionCareer;
	private Spinner cmbSubject;
	private Button btnAddSubject;
	private Button btnConsultSubject;
	private RecyclerView lstSubjects;
	private Button btnSave;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.career_form);

		subjectRepository = DbConnection.getInstance(this.getBaseContext()).getSubjet();
		careerRepository = DbConnection.getInstance(this.getBaseContext()).getCareer();

		txtDescriptionCareer = findViewById(R.id.txtDescriptionCareer);
		cmbSubject = findViewById(R.id.cmbSubject);
		btnAddSubject = findViewById(R.id.btnAddSubject);
		btnConsultSubject = findViewById(R.id.btnConsultSubject);
		lstSubjects = findViewById(R.id.lstSubjects);
		btnSave = findViewById(R.id.btnSaveCareer);

		btnSave.setOnClickListener(this::save);
		btnAddSubject.setOnClickListener(this::addSubject);
		btnConsultSubject.setOnClickListener(event -> {
			Intent intent = new Intent(this.getApplicationContext(), SubjectMainActivity.class);
			startActivity(intent);
		});

		subjectAdapter = new SubjectAdapter(this.getBaseContext(), subjects);
		LinearLayoutManager llm = new LinearLayoutManager(this.getBaseContext());
		lstSubjects.setLayoutManager(llm);
		lstSubjects.setAdapter(subjectAdapter);

		loadSubjects();

	}

	private void save(View view) {

		if (!valid()) {
			return;
		}

		Career career = new Career();
		career.setDescription(txtDescriptionCareer.getText().toString().trim());

		long careerId = careerRepository.persist(career);
		if ( careerId > 0) {
			Toast.makeText(this.getBaseContext(), "Carrera registrada correctamente", Toast.LENGTH_LONG)
			.show();
			saveCareerSubjects(careerId);
			clearComponents();
		}

	}

	private void saveCareerSubjects(long careerId) {

		for (Subject subject : subjects) {
			careerRepository.insertCareerSubject(Integer.parseInt(careerId+""), subject.getId());
		}

	}

	private void loadSubjects() {

		List<Subject> subjects = subjectRepository.findAll();
		subjects.sort((s1, s2) -> s1.getDescription().compareToIgnoreCase(s2.getDescription()));

		ArrayAdapter arrayAdapter = new ArrayAdapter(this.getBaseContext(), R.layout.support_simple_spinner_dropdown_item, subjects);
		cmbSubject.setAdapter(arrayAdapter);
	}

	private void clearComponents() {
		txtDescriptionCareer.setText("");
		loadSubjects();
		subjects.clear();
		subjectAdapter.notifyDataSetChanged();
	}

	@Override
	protected void onResume() {
		super.onResume();
		loadSubjects();
	}

	private Boolean valid() {

		if (txtDescriptionCareer.getText().toString().trim().isEmpty()) {
			txtDescriptionCareer.setError("La descripción no debe estar vacía.");
			return false;
		}

		if (lstSubjects.getAdapter().getItemCount() <= 0) {
			Toast.makeText(this.getBaseContext(), "Debe especificar la(s) asignatura(s).", Toast.LENGTH_LONG)
			.show();
			return false;
		}

		return true;

	}

	private void addSubject(View view) {

		if (!validSubject()) {
			return;
		}


		subjects.add((Subject) cmbSubject.getSelectedItem());
		subjectAdapter.notifyDataSetChanged();

	}

	private Boolean validSubject() {

		if (Objects.isNull(cmbSubject.getSelectedItem())) {
			Toast.makeText(this.getBaseContext(), "Debe seleccionar una asignatura.", Toast.LENGTH_LONG)
			.show();
			return false;
		}

		return true;
	}
}
