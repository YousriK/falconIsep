package com.falcon.avisep.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.falcon.avisep.model.Cours;
import com.falcon.avisep.model.Form;
import com.falcon.avisep.model.UserAvis;
import com.falcon.avisep.repository.ClasseRepository;
import com.falcon.avisep.repository.CoursRepository;
import com.falcon.avisep.repository.FormRepository;
import com.falcon.avisep.repository.QuestionRepository;
import com.falcon.avisep.repository.SalleRepository;
import com.falcon.avisep.repository.UserAvisRepository;
@Service
public class FormService {
	@Autowired
	private FormRepository formRepository;
	@Autowired
	UserAvisServiceImpl userAvisServiceImpl;
	@Autowired
	private UserAvisRepository userRepository;
	@Autowired
	private QuestionRepository question;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private SalleRepository salleRepository;
	@Autowired
	private ClasseRepository classeRepository;
	@Autowired
	private CoursRepository coursRepository;
	public Long createForm(HttpServletRequest request,Form form, Long id){
		if(request.getSession().getAttribute("userLogged")!=null){
			UserAvis user=(UserAvis) request.getSession().getAttribute("userLogged");
			Cours cours=coursRepository.findOne(id);
			System.out.println("Debug "+user.getFirstName());
			form.basicSetUserAvis(user);
			Form f=formRepository.save(form);
			Long formIdLong=f.getId();
			user.addForm(formRepository.findOne(formIdLong));
			
			cours.setForm(f);
			coursRepository.save(cours);
			userRepository.flush();
			return formIdLong;
		}
		return null;
	}
}
