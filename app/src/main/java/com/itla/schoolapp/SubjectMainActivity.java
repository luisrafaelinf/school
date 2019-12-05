package com.itla.schoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itla.schoolapp.adapter.SubjectAdapter;
import com.itla.schoolapp.connection.DbConnection;
import com.itla.schoolapp.entity.Subject;
import com.itla.schoolapp.repository.SubjectRepository;

import java.util.ArrayList;
import java.util.List;

public class SubjectMainActivity extends AppCompatActivity {

	private List<Subject> subjects = new ArrayList<>();
	private SubjectAdapter subjectAdapter;
	private SubjectRepository subjectRepository;
	private Button btnNewSubject;
	private RecyclerView lstSubjects;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subject_main);

		subjectRepository = DbConnection.getInstance(this.getBaseContext()).getSubjet();

		btnNewSubject = findViewById(R.id.btnNewSubject);
		lstSubjects = findViewById(R.id.lstSubjects);

		subjectAdapter = new SubjectAdapter(this.getBaseContext(), subjects);
		LinearLayoutManager llm = new LinearLayoutManager(this.getBaseContext());
		lstSubjects.setLayoutManager(llm);
		lstSubjects.setAdapter(subjectAdapter);

		btnNewSubject.setOnClickListener(event -> {
			Intent intent = new Intent(this.getApplicationContext(), SubjectForm.class);
			startActivity(intent);
		});

		loadSubjects();

	}

	private void loadSubjects() {
		List<Subject> allSubject = subjectRepository.findAll();
		allSubject.sort((s1, s2) -> s1.getDescription().compareToIgnoreCase(s2.getDescription()));

		subjects.clear();
		subjects.addAll(allSubject);
		subjectAdapter.notifyDataSetChanged();
	}

	@Override
	protected void onResume() {
		super.onResume();
		loadSubjects();
	}
}
