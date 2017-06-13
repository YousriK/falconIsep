package com.falcon.avisep.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;

 
@javax.persistence.Entity 
public class Salle implements Serializable
{
	private static final long serialVersionUID = -557912940521481038L;
	 
	@javax.persistence.Column 
	protected String name;

	 
	@javax.persistence.Column 
	protected String location;
	 
	@javax.persistence.ElementCollection 
	protected Set<Long> evaluaters;
	 
	@javax.persistence.ManyToOne 
	@javax.persistence.JoinColumn(nullable = false) 
	protected Cours cours;
	
	
	@javax.persistence.OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="form_id") 
	protected Form form;

	@javax.persistence.Id 
	@javax.persistence.GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long id;

	public Salle(){
		super();
	}
	public Set<Long> getEvaluaters() {
		if(this.evaluaters == null) {
				this.evaluaters = new HashSet<Long>();
		}
		return (Set<Long>) this.evaluaters;
	}
	public void addAllEvaluaters(Set<Long> newEvaluaters) {
		if (this.evaluaters == null) {
			this.evaluaters = new HashSet<Long>();
		}
		this.evaluaters.addAll(newEvaluaters);
	}
	public void removeAllEvaluaters(Set<Integer> newEvaluaters) {
		if(this.evaluaters == null) {
			return;
		}
		
		this.evaluaters.removeAll(newEvaluaters);
	}
	public void addEvaluaters(Long newEvaluaters) {
		if(this.evaluaters == null) {
			this.evaluaters = new HashSet<Long>();
		}
		
		this.evaluaters.add(newEvaluaters);
	}
	public void removeEvaluaters(Integer oldEvaluaters) {
		if(this.evaluaters == null)
			return;
		
		this.evaluaters.remove(oldEvaluaters);
	}
	public void basicSetCours(Cours myCours) {
		if (this.cours != myCours) {
			if (myCours != null){
				if (this.cours != myCours) {
					Cours oldcours = this.cours;
					this.cours = myCours;
					if (oldcours != null)
						oldcours.removeSalle(this);
				}
			}
		}
	}
	
	public void basicSetForm(Form myForm) {
		if (this.form != myForm) {
			if (myForm != null){
				if (this.form != myForm) {
					Form oldform = this.form;
					this.form = myForm;
					if (oldform != null)
						oldform.unsetSalle();
				}
			}
		}
	}
	public Form getForm() {
		return this.form;
	}
	
	public void setForm(Form myForm) {
		this.basicSetForm(myForm);
		myForm.basicSetSalle(this);
		
	}
	public void unsetForm() {
		if (this.form == null)
			return;
		Form oldform = this.form;
		this.form = null;
		oldform.unsetSalle();
	}
	public String getName() {
		return this.name;
	}

	public String getLocation() {
		return this.location;
	}

	public Cours getCours() {
		return this.cours;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String myName) {
		this.name = myName;
	}

	public void setLocation(String myLocation) {
		this.location = myLocation;
	}

	public void setCours(Cours myCours) {
		this.basicSetCours(myCours);
		myCours.addSalle(this);
	}

	public void unsetName() {
		this.name = null;
	}

	public void unsetLocation() {
		this.location = null;
	}

	public void unsetCours() {
		if (this.cours == null)
			return;
		Cours oldcours = this.cours;
		this.cours = null;
		oldcours.removeSalle(this);
	}

}

