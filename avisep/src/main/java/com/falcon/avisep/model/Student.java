package com.falcon.avisep.model;

import java.io.Serializable;

@javax.persistence.Entity 
@javax.persistence.DiscriminatorValue("STUDENT")
public class Student extends UserAvis implements Serializable
{
	private static final long serialVersionUID = 8823018571339295613L;
	 
	@javax.persistence.Column 
	protected Integer promo;

	public Student(){
		super();
	}

	public Integer getPromo() {
		return this.promo;
	}

	public void setPromo(Integer myPromo) {
		this.promo = myPromo;
	}
	public void unsetPromo() {
		this.promo = null;
	}

}

