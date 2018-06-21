package com.ah8.app.ivservice.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ah8.app.ivservice.model.Dicom;
import com.ah8.app.ivservice.model.DicomPatient;
import com.ah8.app.ivservice.model.ExamImage;
import com.ah8.app.ivservice.model.PatientExam;
import com.ah8.app.ivservice.model.Payments;


@Repository
@Transactional(readOnly = false)
public class DicomDao{

	@PersistenceContext	
	EntityManager entityManager;	


	public DicomPatient getPatientById(Integer dicomPatientId){
		DicomPatient dicomPatient=entityManager.find(DicomPatient.class,dicomPatientId);
		return dicomPatient;
	}

	/**
	 * Get Patient Details by MR Number
	 * @param mrNumber
	 * @return
	 */
	public DicomPatient getPatientByMRNumber(String mrNumber){
		TypedQuery<DicomPatient> patientGetQuery = entityManager.createQuery("SELECT dp FROM DicomPatient dp WHERE  dp.mrNumber = :mrNumber ",DicomPatient.class);
		if(mrNumber!=null) {
			patientGetQuery.setParameter("mrNumber", mrNumber);
		}
		List<DicomPatient> patientsList= patientGetQuery.getResultList();
		if(patientsList==null || patientsList.isEmpty()){
			return null;
		}
		return patientsList.get(0);
	}

	/**
	 * Get Patient Examination Details by Examination ID
	 * @param examId
	 * @return
	 */
	public PatientExam getExamdetailsByID(String examId) {
		TypedQuery<PatientExam> patientGetQuery = entityManager.createQuery("SELECT patientExam FROM PatientExam patientExam WHERE  " 
				+ " patientExam.examId = :examId ",PatientExam.class);
		if(examId!=null) {
			patientGetQuery.setParameter("examId", examId);
		}
		List<PatientExam> patientExamList = patientGetQuery.getResultList();
		if(patientExamList==null || patientExamList.isEmpty()){
			return null;
		}
		return patientExamList.get(0);
	}

	/**
	 * Method to add newly Received Dicom image details.
	 * @param dicom
	 * @return
	 */
	public ExamImage addDicomImage(Dicom dicom){

		DicomPatient patient=getPatientByMRNumber(dicom.getMrNumber());
		PatientExam patientExam = getExamdetailsByID(dicom.getExamId());
		ExamImage image=new ExamImage(dicom.getImageDescription(),dicom.getImageOriginalUrl(),dicom.getImageThumbUrl(),dicom.getSequenceNumber(),dicom.getCameraResolution(),dicom.getSourceCamera(),dicom.getReceivedDate(),dicom.getAnnotation());

		if(patient!=null){
			if(patientExam!=null){ 
				//=============== If Patient and Exam is already exists, It will add the image to the exam ==================//
				image.setPatientExam(patientExam);
				entityManager.persist(image);
			}else{
				//=============== If Exam is Not exists, It may be the first image for the exam.=============================//
				//=============== Need to Add both Exam and Image already exists, It will add the image to the exam =========//
				patientExam=new PatientExam(dicom.getExamId(),dicom.getUploadedBy(),dicom.getUploadedDate());
				patientExam.setDicomPatient(patient);
				image.setPatientExam(patientExam);
				Set<ExamImage> imagesList=new HashSet<ExamImage>();
				imagesList.add(image);
				patientExam.setExamImagesList(imagesList);
				entityManager.persist(patientExam);
			}
		}else{
			//======== If Patient and Exam in NOT exist, we need to add both ===============//
			patient=new DicomPatient(dicom.getMrNumber(),dicom.getPatiendId(),dicom.getDob(),dicom.getSex(),dicom.getFirstName(),dicom.getLastName(),dicom.getWeight(),dicom.getAge());
			patientExam=new PatientExam(dicom.getExamId(),dicom.getUploadedBy(),dicom.getUploadedDate());
			patientExam.setDicomPatient(patient);
			image.setPatientExam(patientExam);
			Set<ExamImage> imagesList=new HashSet<ExamImage>();
			imagesList.add(image);
			patientExam.setExamImagesList(imagesList);
			Set<PatientExam> patientExamList=new HashSet<PatientExam>();
			patientExamList.add(patientExam);
			patient.setPatientExamList(patientExamList);
			entityManager.persist(patient);
		}
		return image;
	}

	/**
	 * Method to Search and get the
	 * @param searchString
	 * @return
	 */
	public List<DicomPatient> getDicomDataBySearchString(String searchString){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<DicomPatient> criteriaQuery = criteriaBuilder.createQuery(DicomPatient.class);
		Root<DicomPatient> patient = criteriaQuery.from(DicomPatient.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(criteriaBuilder.like(patient.<String>get("mrNumber"), "%"+searchString+"%"));
		predicates.add(criteriaBuilder.like(patient.<String>get("firstName"), "%"+searchString+"%"));
		predicates.add(criteriaBuilder.like(patient.<String>get("lastName"), "%"+searchString+"%"));
		
		criteriaQuery.select(patient).where(criteriaBuilder.or(predicates.toArray(new Predicate[] {})));
		TypedQuery<DicomPatient> query = entityManager.createQuery(criteriaQuery);
		List<DicomPatient> dicomPatientList = query.getResultList();
		return dicomPatientList;
	}

	public ExamImage addDicomImage(String imageId, String annotations) {
		ExamImage examImage=entityManager.find(ExamImage.class,Integer.parseInt(imageId));
		examImage.setAnnotation(annotations);
		entityManager.merge(examImage);
		return examImage;
	}

	public PatientExam addExamComments(String patientExamId, String leftEyeComments, String rightEyeComments) {
		PatientExam patientExam=entityManager.find(PatientExam.class,Integer.parseInt(patientExamId));
		String leftEyeComm= patientExam.getLeftEyeComments();
		patientExam.setLeftEyeComments(leftEyeComm+" "+leftEyeComments);
		String rightEyeComm = patientExam.getRightEyeComments();
		patientExam.setRightEyeComments(rightEyeComm+" "+rightEyeComments);
		entityManager.merge(patientExam);
		return patientExam;
	}

	public ExamImage getDicomImageDataByID(Integer examImageId) {
		ExamImage examImage=entityManager.find(ExamImage.class,examImageId);
		examImage.getPatientExam();
		examImage.getPatientExam().getDicomPatient();
		return examImage;
	}

	public List<DicomPatient> getDefaultDicomPatients() {
		Query query = entityManager.createQuery("SELECT dp FROM DicomPatient dp");
		@SuppressWarnings("unchecked")
		List<DicomPatient> resultList = (List<DicomPatient>)query.getResultList();
		return resultList;
	}

	public List<PatientExam> getExamsByPatientID(Integer patientId) {
		TypedQuery<PatientExam> patientGetQuery = entityManager.createQuery("SELECT patientExam FROM PatientExam patientExam WHERE  " 
				+ " dicomPatient.dicomPatientId = :patientId ",PatientExam.class);
		if(patientId!=null) {
			patientGetQuery.setParameter("patientId", patientId);
		}
		List<PatientExam> patientExamList = patientGetQuery.getResultList();
		if(patientExamList==null || patientExamList.isEmpty()){
			return null;
		}
		return patientExamList;
	}

	public List<ExamImage> getImagesByExamID(Integer examId) {
		TypedQuery<ExamImage> patientGetQuery = entityManager.createQuery("SELECT examImage FROM ExamImage examImage WHERE  " 
				+ " patientExam.patientExamId = :examId ",ExamImage.class);
		if(examId!=null) {
			patientGetQuery.setParameter("examId", examId);
		}
		List<ExamImage> examImageList = patientGetQuery.getResultList();
		if(examImageList==null || examImageList.isEmpty()){
			return null;
		}
		return examImageList;
	}
	
	public List<Payments> getAllPayments() {
		TypedQuery<Payments> paymentQuery = entityManager.createQuery("SELECT paymments FROM Payments paymments " ,Payments.class);
	
		List<Payments> paymentsList = paymentQuery.getResultList();
		if(paymentsList==null || paymentsList.isEmpty()){
			return null;
		}
		return paymentsList;
	}
	
}
