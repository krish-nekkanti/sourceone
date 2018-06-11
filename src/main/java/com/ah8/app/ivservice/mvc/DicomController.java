package com.ah8.app.ivservice.mvc;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ah8.app.ivservice.model.Dicom;
import com.ah8.app.ivservice.model.DicomPatient;
import com.ah8.app.ivservice.model.ExamImage;
import com.ah8.app.ivservice.model.PatientExam;
import com.ah8.app.ivservice.service.IDicomMeataDataService;

@RestController
public class DicomController {
	
	private static final Logger log = LoggerFactory.getLogger(DicomController.class);

	@Autowired
	private IDicomMeataDataService dicomMeataDataService;

	@RequestMapping("/getDicomDataByID")
	public Response getDicomData(@RequestParam(value = "id",required = false,  defaultValue = "0") Integer id) {
		log.debug("DicomController--->getDicomData -----> start");
		DicomPatient dicomData = dicomMeataDataService.getDicomData(id);
		log.debug("DicomController--->getDicomData -----> End");
		return Response.ok(dicomData, MediaType.APPLICATION_JSON).build();
	}
	
	@RequestMapping("/getDicomImageDataByID")
	public Response getDicomImageDataByID(@RequestParam(value = "id",required = false,  defaultValue = "0") Integer id) {
		log.debug("DicomController--->getDicomImageDataByID -----> start");
		ExamImage examImage = dicomMeataDataService.getDicomImageDataByID(id);
		log.debug("DicomController--->getDicomImageDataByID -----> End");
		return Response.ok(examImage, MediaType.APPLICATION_JSON).build();
	}
	
	@RequestMapping("/getDicomDataByCriteria")
	public Response getDicomDataByCriteria(@RequestParam(value = "id",required = false,  defaultValue = "0") String searchStr) {
		log.debug("DicomController--->getDicomDataByCriteria -----> start");
		List<DicomPatient>  searchPatients=dicomMeataDataService.getDicomDataByCriteria(searchStr);
		if(searchPatients==null || searchPatients.isEmpty()){
			return Response.status(Response.Status.NOT_FOUND).entity("No Patient Details are found for String " +searchStr).build();
		}
		log.debug("DicomController--->getDicomDataByCriteria -----> End");
		return Response.ok(searchPatients, MediaType.APPLICATION_JSON).build();
	}

	@RequestMapping("/addDicomImage")
	public ExamImage addDicomImage() {
		Dicom dicom=new Dicom("MR004","P002",new Date(),"Male","Konda","Reddy,",65,"EX001","Venkat",new Date(),40,"Left Eye2","c://Orig//lefteye1.img","c://THUMB//lefteye1.img",1,"600x720","RETCAM", new Date(),"Nothig");
		return dicomMeataDataService.addDicomImage(dicom);
	}

	@RequestMapping("/addAnnatations")
	public ExamImage addAnnatations(@RequestParam(value = "imageId",required = false,  defaultValue = "0") String imageId,@RequestParam(value = "annotations",required = false,  defaultValue = "") String annotations) {
		return dicomMeataDataService.addAnnatations(imageId,annotations);
	}
	
	@RequestMapping("/addExamComments")
	public PatientExam addExamComments(@RequestParam(value = "patientExamId",required = false,  defaultValue = "0") String patientExamId,@RequestParam(value = "leftEyeComments",required = false,  defaultValue = "") String leftEyeComments,@RequestParam(value = "rightEyeComments",required = false,  defaultValue = "") String rightEyeComments) {
		return dicomMeataDataService.addExamComments(patientExamId,leftEyeComments,rightEyeComments);
	}
	
	@RequestMapping("/getPatients")
	public List<DicomPatient> getDefultDicomPatients() {
		return dicomMeataDataService.getDefaultDicomPatients();
	}
	
	@RequestMapping("/getExamsByPatientID")
	public List<PatientExam> getExamsByPatientID(@RequestParam(value = "patientId",required = false,  defaultValue = "0") Integer patientId) {
		return dicomMeataDataService.getExamsByPatientID(patientId);
	}
	
	@RequestMapping("/getImagesByExamID")
	public List<ExamImage> getImagesByExamID(@RequestParam(value = "examId",required = false,  defaultValue = "0") Integer examId) {
		return dicomMeataDataService.getImagesByExamID(examId);
	}
	
	@RequestMapping("/getAnnotationByImageID")
	public Response getAnnotationByImageID(@RequestParam(value = "imageId",required = false,  defaultValue = "0") Integer imageId) {
		log.debug("DicomController--->getAnnotationByImageID -----> start");
		String imageAnnotation = dicomMeataDataService.getAnnotationByImageID(imageId);
		log.debug("DicomController--->getAnnotationByImageID -----> End");
		return Response.ok(imageAnnotation, MediaType.APPLICATION_JSON).build();
	}
}