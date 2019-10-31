package com.itla.schoolapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.itla.schoolapp.entity.Subject;
import com.itla.schoolapp.repository.SubjectRepository;

import java.util.Objects;

public class SubjectForm extends AppCompatActivity {

	private SubjectRepository subjectRepository;
	private EditText txtDescriptionSubject;
	private EditText txtCredit;
	private Button btnSave;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subject_form);

		subjectRepository = new SubjectRepository(this.getBaseContext());

		txtDescriptionSubject = findViewById(R.id.txtDescriptionSubjects);
		txtCredit = findViewById(R.id.txtCredits);
		btnSave = findViewById(R.id.btnSaveSubject);

		btnSave.setOnClickListener(this::save);

	}

	private void save(View view) {

		if (!valid()) {
			return;
		}

		Subject subject = new Subject();
		subject.setDescription(txtDescriptionSubject.getText().toString().trim());
		subject.setCredits(Integer.parseInt(txtCredit.getText().toString()));


		long id = subjectRepository.persist(subject);

		if (id > 0) {
			Toast.makeText(this.getBaseContext(), "Asignatura registrada correctamente.", Toast.LENGTH_LONG)
			.show();
			clearComponents();
		}

	}

	private boolean valid() {

		if (txtDescriptionSubject.getText().toString().trim().isEmpty()){
			txtDescriptionSubject.setError("La descripción no debe estar vacía.");
			return false;
		}

		String credit = txtCredit.getText().toString().trim();
		if (Objects.isNull(credit) || Integer.parseInt(credit) <= 0) {
			txtCredit.setError("Los creditos deben ser mayor a 0.");
			return false;
		}

		return true;
	}

	private void clearComponents() {
		txtDescriptionSubject.setText("");
		txtCredit.setText(0+"");
	}
}
