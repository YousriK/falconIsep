package com.falcon.avisep.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.GenerationType;
import javax.persistence.Temporal;


@javax.persistence.Entity 
@javax.persistence.Table(name = "form")
public class Form implements Serializable
{
	private static final long serialVersionUID = -7863390379345194681L;

	 
	@javax.persistence.Column 
	protected String formTitle;
	 
	@javax.persistence.Column 
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	protected Date dateCreation;
	 
	@javax.persistence.ManyToMany(mappedBy = "form") 
	protected Set<Question> question;

	 
	@javax.persistence.ManyToOne
	protected UserAvis userAvis;
	
	
	@javax.persistence.OneToOne(mappedBy = "form") 
	protected Module module;
	
	@javax.persistence.OneToOne(mappedBy = "form") 
	protected Cours cours;
	
	@javax.persistence.OneToOne(mappedBy = "form") 
	protected Salle salle;
	
	@javax.persistence.Id 
	@javax.persistence.GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	public Form(){
		super();
	}

	public void basicSetUserAvis(UserAvis myUserAvis) {
		if (this.userAvis != myUserAvis) {
			if (myUserAvis != null){
				if (this.userAvis != myUserAvis) {
					UserAvis olduserAvis = this.userAvis;
					this.userAvis = myUserAvis;
					if (olduserAvis != null)
						olduserAvis.removeForm(this);
				}
			}
		}
	}
	public void basicSetSalle(Salle mySalle) {
		if (this.salle != mySalle) {
			if (mySalle != null){
				if (this.salle != mySalle) {
					Salle oldsalle = this.salle;
					this.salle = mySalle;
					if (oldsalle != null)
						oldsalle.unsetForm();
				}
			}
		}
	}
	public Salle getSalle() {
		return this.salle;
	}
	
	public void setSalle(Salle mySalle) {
		this.basicSetSalle(mySalle);
		mySalle.basicSetForm(this);
		
	}
	
	public void unsetSalle() {
		if (this.salle == null)
			return;
		Salle oldsalle = this.salle;
		this.salle = null;
		oldsalle.unsetForm();
	}

	
	public void basicSetCours(Cours myCours) {
		if (this.cours != myCours) {
			if (myCours != null){
				if (this.cours != myCours) {
					Cours oldcours = this.cours;
					this.cours = myCours;
					if (oldcours != null)
						oldcours.unsetForm();
				}
			}
		}
	}
	public Cours getCours() {
		return this.cours;
	}
	public void setCours(Cours myCours) {
		this.basicSetCours(myCours);
		myCours.basicSetForm(this);
		
	}
	public void unsetCours() {
		if (this.cours == null)
			return;
		Cours oldcours = this.cours;
		this.cours = null;
		oldcours.unsetForm();
	}
	public Module getModule() {
		return this.module;
	}
	public void setModule(Module myModule) {
		this.basicSetModule(myModule);
		myModule.basicSetForm(this);
		
	}
	public void basicSetModule(Module myModule) {
		if (this.module != myModule) {
			if (myModule != null){
				if (this.module != myModule) {
					Module oldmodule = this.module;
					this.module = myModule;
					if (oldmodule != null)
						oldmodule.unsetForm();
				}
			}
		}
	}
	
	public String getFormTitle() {
		return this.formTitle;
	}

	public Date getDateCreation() {
		return this.dateCreation;
	}

	public Set<Question> getQuestion() {
		if(this.question == null) {
				this.question = new HashSet<Question>();
		}
		return (Set<Question>) this.question;
	}
	public UserAvis getUserAvis() {
		return this.userAvis;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public void addAllQuestion(Set<Question> newQuestion) {
		if (this.question == null) {
			this.question = new HashSet<Question>();
		}
		for (Question tmp : newQuestion)
			tmp.addForm(this);
		
	}
	public void removeAllQuestion(Set<Question> newQuestion) {
		if(this.question == null) {
			return;
		}
		
		this.question.removeAll(newQuestion);
	}

	public void setFormTitle(String formTitle) {
		this.formTitle = formTitle;
	}

	public void setDateCreation(Date myDateCreation) {
		this.dateCreation = myDateCreation;
	}

	public void addQuestion(Question newQuestion) {
		if(this.question == null) {
			this.question = new HashSet<Question>();
		}
		
		if (this.question.add(newQuestion))
			newQuestion.addForm(this);
	}
	public void setUserAvis(UserAvis myUserAvis) {
		this.basicSetUserAvis(myUserAvis);
		myUserAvis.addForm(this);
	}

	public void unsetFormTitle() {
		this.formTitle = null;
	}

	public void unsetDateCreation() {
		this.dateCreation = null;
	}

	public void removeQuestion(Question oldQuestion) {
		if(this.question == null)
			return;
		
		if (this.question.remove(oldQuestion))
			oldQuestion.removeForm(this);
		
	}
	public void unsetModule() {
		if (this.module == null)
			return;
		Module oldmodule = this.module;
		this.module = null;
		oldmodule.unsetForm();
	}
	public void unsetUserAvis() {
		if (this.userAvis == null)
			return;
		UserAvis olduserAvis = this.userAvis;
		this.userAvis = null;
		olduserAvis.removeForm(this);
	}

}

