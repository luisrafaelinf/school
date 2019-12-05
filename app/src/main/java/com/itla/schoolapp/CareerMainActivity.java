package com.itla.schoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itla.schoolapp.adapter.CareerAdapter;
import com.itla.schoolapp.connection.DbConnection;
import com.itla.schoolapp.entity.Career;
import com.itla.schoolapp.repository.CareerRepository;

import java.util.ArrayList;
import java.util.List;

public class CareerMainActivity extends AppCompatActivity {

	private List<Career> careers = new ArrayList<>();
	private CareerRepository careerRepository;
	private Button btnNewCareer;
	private RecyclerView lstCareers;
	private CareerAdapter careerAdapter;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("career main","Iniciando activity de carrera");
		setContentView(R.layout.career_main);

		ActionBar supportActionBar = getSupportActionBar();
		supportActionBar.hide();

		careerRepository = DbConnection.getInstance(this.getBaseContext()).getCareer();

		btnNewCareer = findViewById(R.id.btnNewCareer);
		lstCareers = findViewById(R.id.lstCareers);

		careerAdapter = new CareerAdapter(this.getBaseContext(), careers);
		LinearLayoutManager llm = new LinearLayoutManager(this.getBaseContext());
		lstCareers.setLayoutManager(llm);
		lstCareers.setAdapter(careerAdapter);

		loadCareers();

		btnNewCareer.setOnClickListener(event -> {
			Intent intent = new Intent(this.getApplicationContext(), CareerForm.class);
			startActivity(intent);
		});
	}

	private void loadCareers() {

		List<Career> all = careerRepository.findAll();
		careers.clear();
		careers.addAll(all);
		careers.sort((c1, c2) -> c1.getDescription().compareToIgnoreCase(c2.getDescription()));

		careerAdapter.notifyDataSetChanged();
	}

	@Override
	protected void onResume() {
		super.onResume();
		loadCareers();
	}
}
