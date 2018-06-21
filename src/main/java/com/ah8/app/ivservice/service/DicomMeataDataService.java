package com.ah8.app.ivservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ah8.app.ivservice.dao.DicomDao;
import com.ah8.app.ivservice.model.Dicom;
import com.ah8.app.ivservice.model.DicomPatient;
import com.ah8.app.ivservice.model.ExamImage;
import com.ah8.app.ivservice.model.PatientExam;
import com.ah8.app.ivservice.model.Payments;

@Component
public class DicomMeataDataService implements IDicomMeataDataService {
	@Autowired
	private DicomDao dicomDao;


	@Override
	public DicomPatient getDicomData(Integer dicomMastID){
		DicomPatient dicomPatient=dicomDao.getPatientById(dicomMastID);
		return dicomPatient;
	}

	@Override
	public List<DicomPatient> getDicomDataByCriteria(String searchStr) {
		return dicomDao.getDicomDataBySearchString(searchStr);
	}


	@Override
	public ExamImage addDicomImage(Dicom dicom) {
		return dicomDao.addDicomImage(dicom);
	}

	@Override
	public ExamImage addAnnatations(String imageId, String annotations) {
		return dicomDao.addDicomImage(imageId, annotations);

	}

	@Override
	public PatientExam addExamComments(String patientExamId, String leftEyeComments, String rightEyeComments) {
		return dicomDao.addExamComments(patientExamId, leftEyeComments,rightEyeComments);
	}

	@Override
	public ExamImage getDicomImageDataByID(Integer id) {
		return dicomDao.getDicomImageDataByID(id);
	}
	
	@Override
	public List<DicomPatient> getDefaultDicomPatients() {
		List<DicomPatient> dicomPatients= dicomDao.getDefaultDicomPatients();
		/*for(DicomPatient dicomPatient: dicomPatients){
			if(dicomPatient.getPatientExamList().size()>0){
				dicomPatient.setPatientExamList(null);
			}
		}*/
		return dicomPatients;
		
	}

	@Override
	public List<PatientExam> getExamsByPatientID(Integer patientId) {
		List<PatientExam> patientExams= dicomDao.getExamsByPatientID(patientId);
		for(PatientExam patientExam: patientExams){
			if(patientExam.getDicomPatient()!=null){
				patientExam.setDicomPatient(null);
			}
			if(patientExam.getExamImagesList()!=null){
				patientExam.setExamImagesList(null);
			}
		}
		return patientExams;
	}

	@Override
	public List<ExamImage> getImagesByExamID(Integer examId) {
		List<ExamImage> examImages= dicomDao.getImagesByExamID(examId);
		for(ExamImage examImage: examImages){
			if(examImage.getPatientExam()!=null){
				examImage.setPatientExam(null);
			}
			
		}
		return examImages;
	}

	@Override
	public String getAnnotationByImageID(Integer imageId) {
		ExamImage  examImage =  dicomDao.getDicomImageDataByID(imageId);
		String imageAnnotation = examImage.annotation;
		return imageAnnotation;
	}

	@Override
	public List<Payments> getAllPayments() {
		return dicomDao.getAllPayments();
	}
}
