package com.falcon.avisep.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.GenerationType;



@javax.persistence.Entity 
public class Classe implements Serializable
{
	private static final long serialVersionUID = -2848607714935263592L;

	 
	@javax.persistence.Column 
	protected String name;
	 
	@javax.persistence.ManyToMany(mappedBy = "classe") 
	protected Set<UserAvis> userAvis;

	@javax.persistence.Id 
	@javax.persistence.GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long id;

	public Classe(){
		super();
	}

	public String getName() {
		return this.name;
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


	public void addAllUserAvis(Set<UserAvis> newUserAvis) {
		if (this.userAvis == null) {
			this.userAvis = new HashSet<UserAvis>();
		}
		for (UserAvis tmp : newUserAvis)
			tmp.addClasse(this);
		
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


	public void addUserAvis(UserAvis newUserAvis) {
		if(this.userAvis == null) {
			this.userAvis = new HashSet<UserAvis>();
		}
		
		if (this.userAvis.add(newUserAvis))
			newUserAvis.addClasse(this);
	}

	public void unsetName() {
		this.name = null;
	}
	public void removeUserAvis(UserAvis oldUserAvis) {
		if(this.userAvis == null)
			return;
		
		if (this.userAvis.remove(oldUserAvis))
			oldUserAvis.removeClasse(this);
		
	}

}

