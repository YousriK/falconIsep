package com.falcon.avisep.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;

 
@javax.persistence.Entity 
public class Module implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4215320328503290356L;

	 
	@javax.persistence.Column 
	protected String name;

	@javax.persistence.Column 
	protected String description;

	 
	@javax.persistence.OneToMany(mappedBy = "module", cascade = javax.persistence.CascadeType.ALL) 
	protected Set<Cours> cours;

	@javax.persistence.ManyToMany(mappedBy = "module") 
	protected Set<UserAvis> userAvis;
	
	@javax.persistence.OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="form_id")
	protected Form form;
	@javax.persistence.ElementCollection 
	protected Set<Long> evaluaters;
	
	@javax.persistence.Id 
	@javax.persistence.GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long id;

	public Module(){
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
	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}


	public Set<Cours> getCours() {
		if(this.cours == null) {
				this.cours = new HashSet<Cours>();
		}
		return (Set<Cours>) this.cours;
	}

	public Set<UserAvis> getUserAvis() {
		if(this.userAvis == null) {
				this.userAvis = new HashSet<UserAvis>();
		}
		return (Set<UserAvis>) this.userAvis;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void basicSetForm(Form myForm) {
		if (this.form != myForm) {
			if (myForm != null){
				if (this.form != myForm) {
					Form oldform = this.form;
					this.form = myForm;
					if (oldform != null)
						oldform.unsetModule();
				}
			}
		}
	}
	public void addAllCours(Set<Cours> newCours) {
		if (this.cours == null) {
			this.cours = new HashSet<Cours>();
		}
		for (Cours tmp : newCours)
			tmp.setModule(this);
		
	}
	public Form getForm() {
		return this.form;
	}
	public void setForm(Form myForm) {
		this.basicSetForm(myForm);
		myForm.basicSetModule(this);
		
	}
	public void unsetForm() {
		if (this.form == null)
			return;
		Form oldform = this.form;
		this.form = null;
		oldform.unsetModule();
	}

	public void addAllUserAvis(Set<UserAvis> newUserAvis) {
		if (this.userAvis == null) {
			this.userAvis = new HashSet<UserAvis>();
		}
		for (UserAvis tmp : newUserAvis)
			tmp.addModule(this);
		
	}
	public void removeAllCours(Set<Cours> newCours) {
		if(this.cours == null) {
			return;
		}
		
		this.cours.removeAll(newCours);
	}

	public void removeAllUserAvis(Set<UserAvis> newUserAvis) {
		if(this.userAvis == null) {
			return;
		}
		
		this.userAvis.removeAll(newUserAvis);
	}
	public void setName(String myName) {
		this.name = myName;
	}

	public void setDescription(String myDescription) {
		this.description = myDescription;
	}


	public void addCours(Cours newCours) {
		if(this.cours == null) {
			this.cours = new HashSet<Cours>();
		}
		
		if (this.cours.add(newCours))
			newCours.basicSetModule(this);
	}

	public void addUserAvis(UserAvis newUserAvis) {
		if(this.userAvis == null) {
			this.userAvis = new HashSet<UserAvis>();
		}
		
		if (this.userAvis.add(newUserAvis))
			newUserAvis.addModule(this);
	}

	public void unsetName() {
		this.name = null;
	}

	public void unsetDescription() {
		this.description = null;
	}

	public void removeCours(Cours oldCours) {
		if(this.cours == null)
			return;
		
		if (this.cours.remove(oldCours))
			oldCours.unsetModule();
		
	}

	public void removeUserAvis(UserAvis oldUserAvis) {
		if(this.userAvis == null)
			return;
		
		if (this.userAvis.remove(oldUserAvis))
			oldUserAvis.removeModule(this);
		
	}

}

