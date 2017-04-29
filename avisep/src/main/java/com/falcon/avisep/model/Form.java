package com.falcon.avisep.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Form.
 */
@Entity
@Table(name = "form")
public class Form implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long formId;

    @ManyToMany
    @NotNull
    @JoinTable(name = "form_template",
               joinColumns = @JoinColumn(name="forms_formId", referencedColumnName="formId"),
               inverseJoinColumns = @JoinColumn(name="templates_temId", referencedColumnName="temId"))
    private Set<Template> templates = new HashSet<Template>();

    @OneToOne
    @JoinColumn(unique = true)
    private Question question;

    @OneToOne(mappedBy = "form")
    @JsonIgnore
    private VTeacher vTeacher;

    @ManyToOne
    private AdminAvis admin;

    @ManyToOne
    private ETeacher eTeacher;


    public Form() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Form(Set<Template> templates, Question question, VTeacher vTeacher, AdminAvis admin, ETeacher eTeacher) {
		super();
		this.templates = templates;
		this.question = question;
		this.vTeacher = vTeacher;
		this.admin = admin;
		this.eTeacher = eTeacher;
	}

	public Long getFormId() {
		return formId;
	}

	public void setFormId(Long formId) {
		this.formId = formId;
	}

	public VTeacher getvTeacher() {
		return vTeacher;
	}

	public void setvTeacher(VTeacher vTeacher) {
		this.vTeacher = vTeacher;
	}

	public ETeacher geteTeacher() {
		return eTeacher;
	}

	public void seteTeacher(ETeacher eTeacher) {
		this.eTeacher = eTeacher;
	}

	public Set<Template> getTemplates() {
        return templates;
    }

    public Form templates(Set<Template> templates) {
        this.templates = templates;
        return this;
    }

    public Form addTemplate(Template template) {
        this.templates.add(template);
        template.getForms().add(this);
        return this;
    }

    public Form removeTemplate(Template template) {
        this.templates.remove(template);
        template.getForms().remove(this);
        return this;
    }

    public void setTemplates(Set<Template> templates) {
        this.templates = templates;
    }

    public Question getQuestion() {
        return question;
    }

    public Form question(Question question) {
        this.question = question;
        return this;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public VTeacher getVTeacher() {
        return vTeacher;
    }

    public Form vTeacher(VTeacher vTeacher) {
        this.vTeacher = vTeacher;
        return this;
    }

    public void setVTeacher(VTeacher vTeacher) {
        this.vTeacher = vTeacher;
    }

    public AdminAvis getAdmin() {
        return admin;
    }

    public Form admin(AdminAvis adminAvis) {
        this.admin = adminAvis;
        return this;
    }

    public void setAdmin(AdminAvis adminAvis) {
        this.admin = adminAvis;
    }

    public ETeacher getETeacher() {
        return eTeacher;
    }

    public Form eTeacher(ETeacher eTeacher) {
        this.eTeacher = eTeacher;
        return this;
    }

    public void setETeacher(ETeacher eTeacher) {
        this.eTeacher = eTeacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Form form = (Form) o;
        if (form.formId == null || formId == null) {
            return false;
        }
        return Objects.equals(formId, form.formId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(formId);
    }

    @Override
    public String toString() {
        return "Form{" +
            "id=" + formId +
            ", formId='" + formId + "'" +
            '}';
    }
}
