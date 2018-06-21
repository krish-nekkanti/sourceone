package com.ah8.app.ivservice.service;

import java.util.List;

import com.ah8.app.ivservice.model.Dicom;
import com.ah8.app.ivservice.model.DicomPatient;
import com.ah8.app.ivservice.model.ExamImage;
import com.ah8.app.ivservice.model.PatientExam;
import com.ah8.app.ivservice.model.Payments;

public interface IDicomMeataDataService {
	
	public DicomPatient getDicomData(Integer dicomMastID);

	public List<DicomPatient> getDicomDataByCriteria(String searchStr);

	public ExamImage addDicomImage(Dicom dicom);

	public ExamImage addAnnatations(String imageId, String annotations);

	public PatientExam addExamComments(String patientExamId, String leftEyeComments, String rightEyeComments);

	public ExamImage getDicomImageDataByID(Integer id);

	public List<DicomPatient> getDefaultDicomPatients();

	public List<PatientExam> getExamsByPatientID(Integer patientId);

	public List<ExamImage> getImagesByExamID(Integer examId);

	public String getAnnotationByImageID(Integer imageId);

	public List<Payments> getAllPayments();
}
