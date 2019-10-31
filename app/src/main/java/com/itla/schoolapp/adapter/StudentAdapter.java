package com.itla.schoolapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itla.schoolapp.R;
import com.itla.schoolapp.entity.Career;
import com.itla.schoolapp.entity.Student;
import com.itla.schoolapp.repository.CareerRepository;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


	private List<Student> students;
	private CareerRepository careerRepository;

	public StudentAdapter(Context context, List<Student> students) {
		this.students = students;
		careerRepository = new CareerRepository(context);
	}


	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_layout, parent, false);

		return new RecyclerView.ViewHolder(view){};
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
		TextView registerNumber = holder.itemView.findViewById(R.id.registerNumber);
		registerNumber.setText(students.get(position).getRegisterNumber());

		TextView nameStudent = holder.itemView.findViewById(R.id.nameStudent);
		nameStudent.setText(students.get(position).getName());

		TextView career = holder.itemView.findViewById(R.id.career);
		Career careerStudent = careerRepository.find(students.get(position).getCarrerId());
		career.setText(careerStudent.getDescription());

	}


	@Override
	public int getItemCount() {
		return students.size();
	}
}
