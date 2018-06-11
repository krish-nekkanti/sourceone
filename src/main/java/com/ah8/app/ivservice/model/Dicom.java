package com.ah8.app.ivservice.model;


import java.io.Serializable;
import java.util.Date;
public class Dicom implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String  mrNumber;
	public String patiendId;
	public Date dob;
	public String sex;
	public String firstName;
	public String lastName;
	public Integer weight;
	public String  examId;
	public String uploadedBy;
	public Date uploadedDate;
	public Integer age;
	public String imageDescription;
	public String  imageThumbUrl;
	public String imageOriginalUrl;
	public Integer sequenceNumber;
	public String cameraResolution;
	public String sourceCamera;
	private Date receivedDate;
	public String annotation;
	
	

	public Dicom() {
		// TODO Auto-generated constructor stub
		
	}
	
	

	public Dicom(String mrNumber, String patiendId, Date dob, String sex, String firstName, String lastName,
			Integer weight, String examId, String uploadedBy, Date uploadedDate, Integer age, String imageDescription,
			String imageThumbUrl, String imageOriginalUrl, Integer sequenceNumber, String cameraResolution,
			String sourceCamera, Date receivedDate, String annotation) {
		super();
		this.mrNumber = mrNumber;
		this.patiendId = patiendId;
		this.dob = dob;
		this.sex = sex;
		this.firstName = firstName;
		this.lastName = lastName;
		this.weight = weight;
		this.examId = examId;
		this.uploadedBy = uploadedBy;
		this.uploadedDate = uploadedDate;
		this.age = age;
		this.imageDescription = imageDescription;
		this.imageThumbUrl = imageThumbUrl;
		this.imageOriginalUrl = imageOriginalUrl;
		this.sequenceNumber = sequenceNumber;
		this.cameraResolution = cameraResolution;
		this.sourceCamera = sourceCamera;
		this.receivedDate = receivedDate;
		this.annotation = annotation;
	}

	public String getMrNumber() {
		return mrNumber;
	}

	public void setMrNumber(String mrNumber) {
		this.mrNumber = mrNumber;
	}

	public String getPatiendId() {
		return patiendId;
	}

	public void setPatiendId(String patiendId) {
		this.patiendId = patiendId;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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

	@Override
	public String toString() {
		return "Dicom [mrNumber=" + mrNumber + ", patiendId=" + patiendId + ", dob=" + dob + ", sex=" + sex
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", weight=" + weight + ", examId=" + examId
				+ ", uploadedBy=" + uploadedBy + ", uploadedDate=" + uploadedDate + ", age=" + age
				+ ", imageDescription=" + imageDescription + ", imageThumbUrl=" + imageThumbUrl + ", imageOriginalUrl="
				+ imageOriginalUrl + ", sequenceNumber=" + sequenceNumber + ", cameraResolution=" + cameraResolution
				+ ", sourceCamera=" + sourceCamera + ", receivedDate=" + receivedDate + ", annotation=" + annotation
				+ "]";
	}

} 