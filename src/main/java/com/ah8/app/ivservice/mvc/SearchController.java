package com.ah8.app.ivservice.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ah8.app.ivservice.model.DicomPatient;
import com.ah8.app.ivservice.service.IDicomMeataDataService;

@Controller
@RequestMapping("/")
public class SearchController {
	
	private static final Logger log = LoggerFactory.getLogger(SearchController.class);
	
	
	
	@Autowired
	private IDicomMeataDataService dicomMeataDataService;
	
	@RequestMapping("/search")
	public ModelAndView search() {
		DicomPatient dicomData = dicomMeataDataService.getDicomData(1);
		return new ModelAndView("search/search","dicomData",dicomData);
	}
	
	@RequestMapping("/viewer")
	public ModelAndView viewer() {
		//DicomPatient dicomData = dicomMeataDataService.getDicomData(1);
		return new ModelAndView("search/viewer","dicomData",null);
	}
	
	@RequestMapping("/compare")
	public ModelAndView compare() {
		//DicomPatient dicomData = dicomMeataDataService.getDicomData(1);
		return new ModelAndView("search/compare","dicomData",null);
	}
	
	@RequestMapping("/comparePage")
	public ModelAndView comparePage() {
		//DicomPatient dicomData = dicomMeataDataService.getDicomData(1);
		return new ModelAndView("search/viewer1","dicomData",null);
	}
	
	@RequestMapping("/viewer1")
	public ModelAndView viewer1() {
		//DicomPatient dicomData = dicomMeataDataService.getDicomData(1);
		return new ModelAndView("search/viewer1","dicomData",null);
	}
	
	@RequestMapping
	public ModelAndView list() {
		return new ModelAndView("search/list", "messages", null);
	}

}
