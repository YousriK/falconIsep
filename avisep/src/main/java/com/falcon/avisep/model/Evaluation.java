
package com.falcon.avisep.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;


@javax.persistence.Entity 
public class Evaluation implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1749273742540534752L;

	//@javax.persistence.Column(nullable = false)
	@javax.persistence.Column 
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	protected Date eDate;

	@javax.persistence.Column(nullable = true)
	protected String eData;

	@javax.persistence.Column(nullable = true)
	protected Boolean isYesOrNot;


	@javax.persistence.Id 
	@javax.persistence.GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long id;
	 
	@javax.persistence.Column(nullable = true)
	protected Long ownerId;
	 
	@OneToOne
    @PrimaryKeyJoinColumn
	protected Question question;

	
	public Evaluation(){
		super();
	}
	
	public Boolean getIsYesOrNot() {
		return this.isYesOrNot;
	}
	public void setIsYesOrNot(Boolean isYesOrNot) {
		this.isYesOrNot = isYesOrNot;
	}
	public void unsetIsYesOrNot() {
		this.isYesOrNot =  null;
	}

	public Date getEDate() {
		return this.eDate;
	}

	public void setEDate(Date myEData) {
		this.eDate = myEData;
	}
	public void unsetEDate() {
		this.eDate = null;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long setOwnerId(Long userAvis) {
		return this.ownerId=userAvis;
	}


	public void basicSetQuestion(Question myQuestion) {
		if (this.question != myQuestion) {
			if (myQuestion != null){
				if (this.question != myQuestion) {
					Question oldquestion = this.question;
					this.question = myQuestion;
					if (oldquestion != null)
						oldquestion.unsetEvaluation();
				}
			}
		}
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public String getEData() {
		return this.eData;
	}


	public Question getQuestion() {
		return this.question;
	}
	public Long getId() {
		return this.id;
	}

	public void setEData(String myEData) {
		this.eData = myEData;
	}
	public void setQuestion(Question myQuestion) {
		this.basicSetQuestion(myQuestion);
		myQuestion.basicSetEvaluation(this);
		
	}
	public void unsetEData() {
		this.eData = "";
	}


	public void unsetQuestion() {
		if (this.question == null)
			return;
		Question oldquestion = this.question;
		this.question = null;
		oldquestion.unsetEvaluation();
	}

}

