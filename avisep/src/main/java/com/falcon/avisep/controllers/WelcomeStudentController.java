package com.falcon.avisep.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.falcon.avisep.model.Cours;
import com.falcon.avisep.model.Form;
import com.falcon.avisep.model.ModelViewAnsForm;
import com.falcon.avisep.model.Module;
import com.falcon.avisep.model.Question;
import com.falcon.avisep.model.UserAvis;
import com.falcon.avisep.repository.CoursRepository;
import com.falcon.avisep.repository.UserAvisRepository;
import com.falcon.avisep.service.CoursService;
import com.falcon.avisep.service.EvaluationService;
import com.falcon.avisep.service.StudentService;
import com.falcon.avisep.util.utilMethod;
@Controller 
public class WelcomeStudentController {
	@Autowired
	private UserAvisRepository userRepository;
	@Autowired
	CoursRepository coursRepo;
	@Autowired
	StudentService studentService;
	@Autowired
	EvaluationService evaluationService;
	@Autowired
	CoursService coursService;
	private final Logger log = LoggerFactory.getLogger(WelcomeStudentController.class);
	@RequestMapping("/welcomeS")
	public String consultStudent(HttpServletRequest request,Model model){
		if(utilMethod.getBaseUrl(request).equalsIgnoreCase("welcomeS")){

			doit(request, model,((UserAvis )request.getSession().getAttribute("userLogged")).getId());
			return "welcomeStudent";
		}else{
			return "redirect:index";	
		}

	}
	public void doit(HttpServletRequest request, Model model,Long id) {
		//Long id=((UserAvis )request.getSession().getAttribute("userLogged")).getId();
		List<Module> myListModulesByIdUser=studentService.findAllModulesByIdUser(id);
		List<Cours> evaluateds=studentService.findAllEvaluatedsByUser(id);

		List<Module> myListModules=studentService.findAllModulesWithForm();
		List<List<Cours>> myListCours=new ArrayList<>();
		List<Cours> finalListCours=null;
		List<Cours> list=null;

		if(!myListModulesByIdUser.isEmpty()){
			for(int i=0;i<myListModulesByIdUser.size();i++){
				myListCours.add(utilMethod.toList(studentService.findAllCoursByModuleId(myListModulesByIdUser.get(i).getId())));
			}
		}
		if(!myListCours.isEmpty()){
			finalListCours=new ArrayList<>();
			for(int i=0;i<myListCours.size();i++){
				for(int j=0;j<myListCours.get(i).size();j++){
					finalListCours.add(myListCours.get(i).get(j));
				}
			}
				
		}
		list=studentService.findAllAvailableByUser(id, finalListCours);
		model.addAttribute("myListModulesByIdUser", myListModulesByIdUser);
		model.addAttribute("finalListCours", list);
		model.addAttribute("evaluations", evaluateds);
	}
	@RequestMapping("/welcomeS/answerF/{id}/{decription}")
	public String ansForm(Model model,@PathVariable("id") Long coursId,@PathVariable("decription") String description){
		Cours cours=coursService.findACours(coursId);
		Form forms=cours.getForm();
		List<Question> question=utilMethod.toList(forms.getQuestion());
		ModelViewAnsForm modelViewAnsForm=new ModelViewAnsForm(cours,forms, question,forms.getFormTitle());
		for(int i=0;i<modelViewAnsForm.getOptionById().size();i++){
			System.out.println(modelViewAnsForm.getOptionById().values());
		}

		model.addAttribute("modelViewAnsForm", modelViewAnsForm);
		model.addAttribute("id", coursId);
		model.addAttribute("description", description);
		return "answerForm";
	}
	@RequestMapping(value="welcomeS/saveEvaluation/{coursId}/{userId}",method = RequestMethod.POST)
	public void saveEvaluation(HttpServletRequest request, HttpServletResponse response,Model model,@RequestBody String evaluations,@PathVariable("coursId") String fid,@PathVariable("userId") String sid) throws JSONException{
		
		Long coursId=Long.valueOf(fid);
		Long userId=Long.valueOf(sid);
		Cours cours=coursService.findACours(coursId);
		cours.addEvaluaters(userId);
		coursRepo.save(cours);
		JSONObject jsonObj = new JSONObject(evaluations);
		String s = jsonObj.getString("paramJson");
		JSONObject json = new JSONObject(s);
		Map<String, Object> map = utilMethod.toMap(json);
		List<String> keysList = new ArrayList<String>(map.keySet());
		List<Object> valuesList = utilMethod.takeValuesFromMap(keysList, map);

		//		System.out.println(keysList);System.out.println();
		//		System.out.println(valuesList);System.out.println();
		//		System.out.println(coursId);
		//		System.out.println(userId);

		evaluationService.saveEvaluation(userId,keysList,valuesList,coursId);
		//model.addAttribute("coursId", coursId);
		//doit(request,model,userId);
	}


}

