package com.itla.schoolapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itla.schoolapp.R;
import com.itla.schoolapp.connection.DbConnection;
import com.itla.schoolapp.entity.Subject;
import com.itla.schoolapp.repository.SubjectRepository;

import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private SubjectRepository subjectRepository;
	private List<Subject> subjects;

	public SubjectAdapter(Context context, List<Subject> subjects) {
		this.subjects = subjects;
		this.subjectRepository = DbConnection.getInstance(context).getSubjet();
	}

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_layout, parent, false);
		return new RecyclerView.ViewHolder(view) {};
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
		View view = holder.itemView;
		Subject subject = subjects.get(position);
		TextView txtDescriptionSubject = view.findViewById(R.id.txtDescriptionSubject);
		txtDescriptionSubject.setText(subject.getDescription());

		TextView txtTotalCareer = view.findViewById(R.id.txtTotalCareer);
		txtTotalCareer.setText(subjectRepository.getTotalCareers(subject.getId())+" Carrera(s)");

		TextView txtCredit = view.findViewById(R.id.txtCredit);
		txtCredit.setText(subject.getCredits()+" Crédito(s)");

	}

	@Override
	public int getItemCount() {
		return subjects.size();
	}
}
