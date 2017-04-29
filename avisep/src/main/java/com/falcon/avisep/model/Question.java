package com.falcon.avisep.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A Question.
 */
@Entity
@Table(name = "question")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long qId;


    @Column(name = "in_template")
    private String inTemplate;

    @Column(name = "as_pertinance")
    private String asPertinance;

    @Column(name = "is_pertinent")
    private Boolean isPertinent;

    @Column(name = "content")
    private String content;

    @Column(name = "q_type")
    private String qType;

    @OneToMany(mappedBy = "question")
    @JsonIgnore
    private Set<Evaluation> evaluations = new HashSet<Evaluation>();

    @OneToOne(mappedBy = "question")
    @JsonIgnore
    private Form form;

    @ManyToMany(mappedBy = "questions")
    @JsonIgnore
    private Set<Template> templates = new HashSet<Template>();


    public Question(String inTemplate, String asPertinance, Boolean isPertinent, String content, String qType,
			Set<Evaluation> evaluations, Form form, Set<Template> templates) {
		super();
		this.inTemplate = inTemplate;
		this.asPertinance = asPertinance;
		this.isPertinent = isPertinent;
		this.content = content;
		this.qType = qType;
		this.evaluations = evaluations;
		this.form = form;
		this.templates = templates;
	}

	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getqId() {
		return qId;
	}

	public void setqId(Long qId) {
		this.qId = qId;
	}

	public Boolean getIsPertinent() {
		return isPertinent;
	}

	public String getInTemplate() {
        return inTemplate;
    }

    public Question inTemplate(String inTemplate) {
        this.inTemplate = inTemplate;
        return this;
    }

    public void setInTemplate(String inTemplate) {
        this.inTemplate = inTemplate;
    }

    public String getAsPertinance() {
        return asPertinance;
    }

    public Question asPertinance(String asPertinance) {
        this.asPertinance = asPertinance;
        return this;
    }

    public void setAsPertinance(String asPertinance) {
        this.asPertinance = asPertinance;
    }

    public Boolean isIsPertinent() {
        return isPertinent;
    }

    public Question isPertinent(Boolean isPertinent) {
        this.isPertinent = isPertinent;
        return this;
    }

    public void setIsPertinent(Boolean isPertinent) {
        this.isPertinent = isPertinent;
    }

    public String getContent() {
        return content;
    }

    public Question content(String content) {
        this.content = content;
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getqType() {
        return qType;
    }

    public Question qType(String qType) {
        this.qType = qType;
        return this;
    }

    public void setqType(String qType) {
        this.qType = qType;
    }

    public Set<Evaluation> getEvaluations() {
        return evaluations;
    }

    public Question evaluations(Set<Evaluation> evaluations) {
        this.evaluations = evaluations;
        return this;
    }

    public Question addEvaluation(Evaluation evaluation) {
        this.evaluations.add(evaluation);
        evaluation.setQuestion(this);
        return this;
    }

    public Question removeEvaluation(Evaluation evaluation) {
        this.evaluations.remove(evaluation);
        evaluation.setQuestion(null);
        return this;
    }

    public void setEvaluations(Set<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public Form getForm() {
        return form;
    }

    public Question form(Form form) {
        this.form = form;
        return this;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public Set<Template> getTemplates() {
        return templates;
    }

    public Question templates(Set<Template> templates) {
        this.templates = templates;
        return this;
    }

    public Question addTemplate(Template template) {
        this.templates.add(template);
        template.getQuestions().add(this);
        return this;
    }

    public Question removeTemplate(Template template) {
        this.templates.remove(template);
        template.getQuestions().remove(this);
        return this;
    }

    public void setTemplates(Set<Template> templates) {
        this.templates = templates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Question question = (Question) o;
        if (question.qId == null || qId == null) {
            return false;
        }
        return Objects.equals(qId, question.qId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(qId);
    }

    @Override
    public String toString() {
        return "Question{" +
            "id=" + qId +
            ", qId='" + qId + "'" +
            ", inTemplate='" + inTemplate + "'" +
            ", asPertinance='" + asPertinance + "'" +
            ", isPertinent='" + isPertinent + "'" +
            ", content='" + content + "'" +
            ", qType='" + qType + "'" +
            '}';
    }
}
