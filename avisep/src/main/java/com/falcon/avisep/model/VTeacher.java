package com.falcon.avisep.model;

import java.io.Serializable;

@javax.persistence.Entity 
@javax.persistence.DiscriminatorValue("VTEACHER")
public class VTeacher extends UserAvis implements Serializable
{
	private static final long serialVersionUID = 411908557648289698L;

	public VTeacher(){
		super();
	}

}

