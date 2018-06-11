package com.ah8.app.ivservice.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ah8.app.ivservice.component.JsonDateSerializer;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@Table(name="EXAM_IMAGE")
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@examImageId")
public class ExamImage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="EXAM_IMAGE_ID")
	public Integer examImageId;
	
	@Column(name="IMAGE_DESCRIPTION")
	public String imageDescription;
	
	@Column(name="IMAGE_THUMB_URL")
	public String  imageThumbUrl;
	
	@Column(name="IMAGE_ORIGNAL_URL")
	public String imageOriginalUrl;
	
	@Column(name="SEQUENCE_NUMBER")
	public Integer sequenceNumber;
	
	@Column(name="CAMERA_RESOUTION")
	public String cameraResolution;

	@Column(name="SOURCE_CAMERA")
	public String sourceCamera;
	
	@JsonSerialize(using=JsonDateSerializer.class)
	@Column(name = "RECEIVED_DATE", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date receivedDate;
	
	@Column(name="ANNOTATION")
	public String annotation;
	
	//@JsonIgnore
	
	//@JsonBackReference
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	@JoinColumn(name = "PATIENT_EXAM_ID")
	private PatientExam patientExam;
	
	public ExamImage() {
		// TODO Auto-generated constructor stub
	}

	
	public ExamImage(String imageDescription, String imageThumbUrl, String imageOriginalUrl,
			Integer sequenceNumber, String cameraResolution, String sourceCamera, Date receivedDate, String annotation) {
		super();
		this.imageDescription = imageDescription;
		this.imageThumbUrl = imageThumbUrl;
		this.imageOriginalUrl = imageOriginalUrl;
		this.sequenceNumber = sequenceNumber;
		this.cameraResolution = cameraResolution;
		this.sourceCamera = sourceCamera;
		this.receivedDate = receivedDate;
		this.annotation = annotation;
	}

	public Integer getExamImageId() {
		return examImageId;
	}


	public void setExamImageId(Integer examImageId) {
		this.examImageId = examImageId;
	}


	public String getImageDescription() {
		return imageDescription;
	}

	public void setImageDescription(String imageDescription) {
		this.imageDescription = imageDescription;
	}

	public String getImageThumbUrl() {
		return imageThumbUrl;
	}

	public void setImageThumbUrl(String imageThumbUrl) {
		this.imageThumbUrl = imageThumbUrl;
	}

	public String getImageOriginalUrl() {
		return imageOriginalUrl;
	}

	public void setImageOriginalUrl(String imageOriginalUrl) {
		this.imageOriginalUrl = imageOriginalUrl;
	}

	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getCameraResolution() {
		return cameraResolution;
	}

	public void setCameraResolution(String cameraResolution) {
		this.cameraResolution = cameraResolution;
	}

	public String getSourceCamera() {
		return sourceCamera;
	}

	public void setSourceCamera(String sourceCamera) {
		this.sourceCamera = sourceCamera;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getAnnotation() {
		return annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}

	public PatientExam getPatientExam() {
		return patientExam;
	}

	public void setPatientExam(PatientExam patientExam) {
		this.patientExam = patientExam;
	}


	@Override
	public String toString() {
		return "PatientReviewImage [examImageId=" + examImageId + ", "
				 + ", imageDescription=" + imageDescription + ", imageThumbUrl=" + imageThumbUrl
				+ ", imageOriginalUrl=" + imageOriginalUrl + ", sequenceNumber=" + sequenceNumber
				+ ", cameraResolution=" + cameraResolution + ", sourceCamera=" + sourceCamera + ", receivedDate="
				+ receivedDate + ", annotation=" + annotation + "]";
	}
	
} 