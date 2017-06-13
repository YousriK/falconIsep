package com.falcon.avisep.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.falcon.avisep.util.utilMethod;

public class ModelViewAnsForm {
	protected Cours cours;
	protected Form forms;
	protected List<Question> question;
	protected List<Evaluation> evaluation;
	protected Map<Long, List<String>> optionById = new HashMap<Long, List<String>>();
	protected String formTitle;

	
	public ModelViewAnsForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ModelViewAnsForm(Cours cours, Form forms, List<Question> question, String formTitle) {
		super();
		this.cours = cours;
		this.forms = forms;
		this.question=new ArrayList<Question>();
		
		for(int i=0;i<question.size();i++){
			if(question.get(i)==null)
				question.remove(i);
				//question.get(i).getEvaluation()
		}
		
		this.question=question;
		this.optionById = getOptionById();
		this.formTitle = formTitle;
	}
	public String getFormTitle() {
		return formTitle;
	}
	public void setFormTitle(String formTitle) {
		this.formTitle = formTitle;
	}
	public Cours getCours() {
		return cours;
	}
	public void setCours(Cours cours) {
		this.cours = cours;
	}
	public Form getForms() {
		return forms;
	}
	public void setForms(Form forms) {
		this.forms = forms;
	}
	public List<Question> getQuestion() {
		return question;
	}
	public void setQuestion(List<Question> question) {
		this.question = question;
	}
	
	public Map<Long, List<String>> getOptionById() {
		Map<Long, List<String>> optionById = new HashMap<Long, List<String>>();
		for(int i=0;i<this.question.size();i++)
		{
			optionById.put(this.question.get(i).getId(),utilMethod.parseOptions(this.question.get(i).getOptions()));
		}
		return optionById;
	}
	public List<Evaluation> getEvaluation() {
		return evaluation;
	}
	
	
	public void setEvaluation(List<Evaluation> evaluation) {
		this.evaluation = evaluation;
	}
	public Map<Long, Object> setEvaluationById() {
		
		
		
		Map<Long, Object> optionById = new HashMap<Long, Object>();
		for(int i=0;i<this.question.size();i++)
		{
			optionById.put(this.question.get(i).getId(),utilMethod.parseOptions(this.question.get(i).getOptions()));
		}
		return optionById;
	}
	public void setOptionById(Map<Long, List<String>> options) {
		this.optionById = options;
	}
	@Override
	public String toString() {
		return "ModelViewAnsForm [cours=" + cours + ", forms=" + forms + ", question=" + question + ", options="
				+ optionById + ", formTitle=" + formTitle + "]";
	}
	
	
	
	
}
