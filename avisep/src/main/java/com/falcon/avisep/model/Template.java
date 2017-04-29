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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A Template.
 */
@Entity
@Table(name = "template")
public class Template implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long temId;

   

	@Column(name = "name")
    private String name;

    @ManyToMany
    @NotNull
    @JoinTable(name = "template_question",
               joinColumns = @JoinColumn(name="templates_temId", referencedColumnName="temId"),
               inverseJoinColumns = @JoinColumn(name="questions_qId", referencedColumnName="qId"))
    private Set<Question> questions = new HashSet<Question>();

    @ManyToMany(mappedBy = "templates")
    @JsonIgnore
    private Set<Form> forms = new HashSet<Form>();

    public Template(String name, Set<Question> questions, Set<Form> forms) {
		super();
		this.name = name;
		this.questions = questions;
		this.forms = forms;
	}

	public Template() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getTemId() {
		return temId;
	}

	public void setTemId(Long temId) {
		this.temId = temId;
	}

    public String getName() {
        return name;
    }

    public Template name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public Template questions(Set<Question> questions) {
        this.questions = questions;
        return this;
    }

    public Template addQuestion(Question question) {
        this.questions.add(question);
        question.getTemplates().add(this);
        return this;
    }

    public Template removeQuestion(Question question) {
        this.questions.remove(question);
        question.getTemplates().remove(this);
        return this;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Set<Form> getForms() {
        return forms;
    }

    public Template forms(Set<Form> forms) {
        this.forms = forms;
        return this;
    }

    public Template addForm(Form form) {
        this.forms.add(form);
        form.getTemplates().add(this);
        return this;
    }

    public Template removeForm(Form form) {
        this.forms.remove(form);
        form.getTemplates().remove(this);
        return this;
    }

    public void setForms(Set<Form> forms) {
        this.forms = forms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Template template = (Template) o;
        if (template.temId == null || temId == null) {
            return false;
        }
        return Objects.equals(temId, template.temId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(temId);
    }

    @Override
    public String toString() {
        return "Template{" +
            "id=" + temId +
            ", temId='" + temId + "'" +
            ", name='" + name + "'" +
            '}';
    }
}
