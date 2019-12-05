package com.itla.schoolapp.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;

@Entity(tableName = "career_subject",
	primaryKeys = {"career_id", "subject_id"},
	foreignKeys = {
		@ForeignKey(entity = Career.class,
			parentColumns = "id",
			childColumns = "career_id"),
		@ForeignKey(entity = Subject.class,
			parentColumns = "id",
			childColumns = "subject_id")
	},
	indices = {
		@Index(value = {"career_id", "subject_id"}, unique = true)
	})
public class CareerSubject {


	@ColumnInfo(name = "career_id")
	@NonNull
	private Integer careerId;

	@ColumnInfo(name = "subject_id", index = true)
	@NonNull
	private Integer subjectId;

	@Ignore
	private Career career;

	@Ignore
	private Subject subject;

	public CareerSubject() {
	}

	@Ignore
	public CareerSubject(Career career, Subject subject) {
		this.career = career;
		this.subject = subject;
		this.careerId = career.getId();
		this.subjectId = subject.getId();
	}

	public Integer getCareerId() {
		return careerId;
	}

	public void setCareerId(Integer careerId) {
		this.careerId = careerId;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public Career getCareer() {
		return career;
	}

	public void setCareer(Career career) {
		this.career = career;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
}

