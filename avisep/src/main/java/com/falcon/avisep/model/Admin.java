package com.falcon.avisep.model;

import java.io.Serializable;

 
@javax.persistence.Entity 
@javax.persistence.DiscriminatorValue("ADMIN")
public class Admin extends UserAvis implements Serializable
{
	private static final long serialVersionUID = 4814306147966430302L;

	public Admin(){
		super();
	}

}

