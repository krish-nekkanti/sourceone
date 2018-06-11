package com.ah8.app.ivservice.model;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@Table(name="PATIENT_EXAM")
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@patientExamId")
public class PatientExam implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PATIENT_EXAM_ID")
	public Integer patientExamId;
	
	@Column(name="EXAM_ID")
	public String  examId;
	
	@Column(name="UPLOADED_BY")
	public String uploadedBy;
	
	@Column(name="UPLOADED_DATE")
	public Date uploadedDate;
	
	@Column(name="LEFT_EYE_COMMENTS")
	public String leftEyeComments;
	
	@Column(name="RIGHT_EYE_COMMENTS")
	public String rightEyeComments;
	
	//@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	@JoinColumn(name = "DICOM_PATIENT_ID")
	private DicomPatient dicomPatient;
	
	//@JsonIgnore
	//@JsonManagedReference
	@OneToMany(mappedBy="patientExam", cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	private Set<ExamImage> examImagesList;

	public PatientExam() {
	}

	public PatientExam( String examId, String uploadedBy,Date uploadedDate) {
		super();
		this.examId = examId;
		this.uploadedBy = uploadedBy;
		this.uploadedDate=uploadedDate;
	}	
	public Integer getPatientExamId() {
		return patientExamId;
	}

	public void setPatientExamId(Integer patientExamId) {
		this.patientExamId = patientExamId;
	}

	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public String getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	public Date getUploadedDate() {
		return uploadedDate;
	}

	public void setUploadedDate(Date uploadedDate) {
		this.uploadedDate = uploadedDate;
	}

	public DicomPatient getDicomPatient() {
		return dicomPatient;
	}

	public void setDicomPatient(DicomPatient dicomPatient) {
		this.dicomPatient = dicomPatient;
	}
	public Set<ExamImage> getExamImagesList() {
		return examImagesList;
	}

	public void setExamImagesList(Set<ExamImage> examImagesList) {
		this.examImagesList = examImagesList;
	}
	
	public String getLeftEyeComments() {
		return leftEyeComments;
	}

	public void setLeftEyeComments(String leftEyeComments) {
		this.leftEyeComments = leftEyeComments;
	}

	public String getRightEyeComments() {
		return rightEyeComments;
	}

	public void setRightEyeComments(String rightEyeComments) {
		this.rightEyeComments = rightEyeComments;
	}

	@Override
	public String toString() {
		return "DicomPatientReview [patientExamId=" + patientExamId +  ", examId=" + examId + ", uploadedBy=" + uploadedBy + ", uploadedDate=" + uploadedDate+"]";
	}

	
} 