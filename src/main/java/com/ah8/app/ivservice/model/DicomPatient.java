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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@Table(name="dicom_patient")
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@dicomPatientId")
public class DicomPatient implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DICOM_PATIENT_ID")
	public Integer dicomPatientId;

	@Column(name="MR_NUMBER")
	public String  mrNumber;

	@Column(name="PATIENT_ID")
	public String patiendId;

	@Column(name="DOB")
	public Date dob;

	@Column(name="SEX")
	public String sex;

	@Column(name="FIRSTNAME")
	public String firstName;

	@Column(name="LASTNAME")
	public String lastName;

	@Column(name="WEIGHT")
	public Integer weight;

	@Column(name="AGE")
	public Integer age;

	@OneToMany(mappedBy="dicomPatient", cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	private Set<PatientExam> patientExamList;  

	public DicomPatient() {
	}

	public DicomPatient(String mrNumber, String patiendId, Date dob, String sex, String firstName, String lastName,
			Integer weight, Integer age) {
		super();
		this.mrNumber = mrNumber;
		this.patiendId = patiendId;
		this.dob = dob;
		this.sex = sex;
		this.firstName = firstName;
		this.lastName = lastName;
		this.weight = weight;
		this.age = age;
	}

	public Integer getDicomPatientId() {
		return dicomPatientId;
	}

	public void setDicomPatientId(Integer dicomPatientId) {
		this.dicomPatientId = dicomPatientId;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Set<PatientExam> getPatientExamList() {
		return patientExamList;
	}

	public void setPatientExamList(Set<PatientExam> patientExamList) {
		this.patientExamList = patientExamList;
	}

	@Override
	public String toString() {
		return "DicomPatient [dicomPatientId=" + dicomPatientId + ", mrNumber=" + mrNumber + ", patiendId=" + patiendId
				+ ", dob=" + dob + ", sex=" + sex + ", firstName=" + firstName + ", lastName=" + lastName + ", weight="
				+ weight + ", age=" + age + ", dicomPatientReviewList=" + patientExamList + "]";
	}

	
} 