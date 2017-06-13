package com.falcon.avisep.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;



 
@javax.persistence.Entity 
public class Question implements Serializable
{
	private static final long serialVersionUID = 9073731474395915309L;

	@javax.persistence.Column 
	protected String options;
	 
	@javax.persistence.Column 
	protected Boolean isPertinent;
	 
	@javax.persistence.Column 
	protected String qTitle;
	 
	@javax.persistence.Column 
	protected String qType;
	 
	@javax.persistence.ManyToMany 
	protected Set<Form> form;

	@OneToOne(mappedBy="question", cascade = CascadeType.ALL)
	protected Evaluation evaluation;

	@javax.persistence.Id 
	@javax.persistence.GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long id;

	public Question(){
		super();
	}

	public Boolean getIsPertinent() {
		return this.isPertinent;
	}


	public String getqTitle() {
		return qTitle;
	}
	public Evaluation getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}
	
	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}

	public String getQType() {
		return this.qType;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public Set<Form> getForm() {
		if(this.form == null) {
				this.form = new HashSet<Form>();
		}
		return (Set<Form>) this.form;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void addAllForm(Set<Form> newForm) {
		if (this.form == null) {
			this.form = new HashSet<Form>();
		}
		for (Form tmp : newForm)
			tmp.addQuestion(this);
		
	}
	public void removeAllForm(Set<Form> newForm) {
		if(this.form == null) {
			return;
		}
		
		this.form.removeAll(newForm);
	}


	public void setIsPertinent(Boolean myIsPertinent) {
		this.isPertinent = myIsPertinent;
	}


	public void setQType(String myQType) {
		this.qType = myQType;
	}

	public void addForm(Form newForm) {
		if(this.form == null) {
			this.form = new HashSet<Form>();
		}
		
		if (this.form.add(newForm))
			newForm.addQuestion(this);
	}
	

	public void unsetIsPertinent() {
		this.isPertinent = null;
	}
	public void unsetQTitle() {
		this.qTitle = null;
	}
	public void unsetEvaluation() {
		this.evaluation = null;
	}

	public void unsetQType() {
		this.qType = null;
	}

	public void removeForm(Form oldForm) {
		if(this.form == null)
			return;
		
		if (this.form.remove(oldForm))
			oldForm.removeQuestion(this);
		
	}
	public void basicSetEvaluation(Evaluation myEvaluation) {
		if (this.evaluation != myEvaluation) {
			if (myEvaluation != null){
				if (this.evaluation != myEvaluation) {
					Evaluation oldevaluation = this.evaluation;
					this.evaluation = myEvaluation;
					if (oldevaluation != null)
						oldevaluation.unsetQuestion();
				}
			}
		}
	}

}

