package com.itla.schoolapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itla.schoolapp.R;
import com.itla.schoolapp.entity.Career;
import com.itla.schoolapp.repository.CareerRepository;

import java.util.List;

public class CareerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private CareerRepository careerRepository;
	private List<Career> careers;

	public CareerAdapter(Context context, List<Career> careers) {
		this.careers = careers;
		careerRepository = new CareerRepository(context);
	}

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.career_layout, parent, false);

		return new RecyclerView.ViewHolder(view) {};
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
		View view = holder.itemView;
		Career career = careers.get(holder.getAdapterPosition());

		TextView txtDescription = view.findViewById(R.id.txtDescription);
		txtDescription.setText(careers.get(position).getDescription());

		TextView txtTotalSubject = view.findViewById(R.id.txtTotalSubject);
		long totalSubjects = careerRepository.getTotalSubjects(career.getId());
		txtTotalSubject.setText(totalSubjects + " Asignatura(s)");

		TextView txtTotalCredit = view.findViewById(R.id.txtTotalCredit);
		long totalCredits = careerRepository.getTotalCredits(career.getId());
		txtTotalCredit.setText(totalCredits+" credito(s)");

	}

	@Override
	public int getItemCount() {
		return careers.size();
	}
}
