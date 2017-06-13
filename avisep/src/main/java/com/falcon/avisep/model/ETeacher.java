package com.falcon.avisep.model;

import java.io.Serializable;

 
@javax.persistence.Entity 
@javax.persistence.DiscriminatorValue("ETEACHER")
public class ETeacher extends UserAvis implements Serializable
{
	private static final long serialVersionUID = 3202234244498869333L;

	public ETeacher(){
		super();
	}

}

