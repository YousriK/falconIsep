package com.falcon.avisep.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.falcon.avisep.model.Form;
import com.falcon.avisep.model.Question;
import com.falcon.avisep.service.FormService;
import com.falcon.avisep.service.QuestionService;
import com.falcon.avisep.util.utilMethod;
@Controller 
public class FormController {
	@Autowired
	FormService formService;

	private final Logger log = LoggerFactory.getLogger(FormController.class);
	@Autowired
	QuestionService questionService;
	// Create and display forms
	@RequestMapping(value= "createF/{id}", method = RequestMethod.POST)
	public  String cForm(HttpServletRequest request,@RequestBody Map<String, Object> jsonObjectBuilderForm,@PathVariable("id") Long id, Model model){
		Form form=new Form();
//System.out.println(jsonObjectBuilderForm.toString());
		List<String> listS = utilMethod.takeListFromMap(jsonObjectBuilderForm);
		List<Question> questions = new ArrayList<Question>();
		for(int i=0;i<listS.size();i++){
			String[] qType=new String[2];
			List<String> listChamps=utilMethod.takeChamps(listS,i);
			Question e=new Question();
			for(int j=0;j<listChamps.size();j++){
				//System.out.println(listChamps.get(j));
				switch (j)
				{
				case 0:
					e.setQType(listChamps.get(j));
					//System.out.println("Type :"+listChamps.get(j));
					break;
				case 1:
					qType[0]=listChamps.get(j); 
					//System.out.println("Title :"+listChamps.get(j));
					break;
				default:
					qType[1]=listChamps.get(j);
					//System.out.println("Options :"+listChamps.get(j));
				}

			}
			e.setqTitle(qType[0]);
			qType[1]=qType[1]!=null?qType[1]:"";
			
			
			e.setOptions(qType[1]);
			System.out.println(e.getqTitle()+" "+e.getOptions());
			questions.add(e);
		}
		Set<Question> q=new HashSet<Question>(questionService.saveQuestion(questions));
		form.addAllQuestion(q);
		String myFormTitle=utilMethod.takeFromTitle(jsonObjectBuilderForm);
		form.setFormTitle(myFormTitle);
		Date myDateCreation =  new Date();
		form.setDateCreation(myDateCreation);
		formService.createForm(request, form, id);
		return "redirect:/createForm";
	}
	@RequestMapping(value= "createF/{id}", method = RequestMethod.GET)
	public  String cForm1(HttpServletRequest request,@PathVariable("id") Long id, Model model){
		model.addAttribute("id", id);
		return "createForm";
	}
	public static List<String> getF(List<String> listS) {
		List<String> l=new ArrayList<String>();
		for(int i=0;i<4;i++){
			List<String> listChamps=utilMethod.takeChamps(listS,i);
			for(int j=0;j<listChamps.size();j++){
				//System.out.println(listChamps.get(j));
				switch (j)
				{
				case 0:
					l.add(j,listChamps.get(j));
					System.out.println("Type :"+listChamps.get(j));
					break;
				case 1:
					l.add(j,listChamps.get(j));
					System.out.println("Title :"+listChamps.get(j));
					break;
				default:
					l.add(j,listChamps.get(j));
					System.out.println("Options :"+listChamps.get(j));
				}
			}
		}

		return listS;
	}
	//Create and display forms

}
//entry key 0: title
//Object value 0: Formulaire BDD
//entry key 1: questions
//Object value 1: [{type=text, title=O que é o amor ?}, {type=checkbox, title=Gostas dela ?, options=[Nao, Sim, Confuso]}, {type=yn, title=Diga apenas sim ou nao?}, {type=radio, title=Sabes dancar?, options=[Em minha casa, fora de casa]}]
//type=text, title=O que é o amor ?
//type=checkbox, title=Gostas dela ?, options=[Nao, Sim, Confuso]
//type=yn, title=Diga apenas sim ou nao?
//type=radio, title=Sabes dancar?, options=[Em minha casa, fora de casa]