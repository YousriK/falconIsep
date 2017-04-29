package com.falcon.avisep.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * A Evaluation.
 */
@Entity
@Table(name = "evaluation")
public class Evaluation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long evaluationId;

    @Column(name = "e_data")
    private String eData;

    @ManyToOne
    private Salle salle;

    @ManyToOne
    private Cours cours;

    @ManyToOne
    private Module module;

    @ManyToOne
    private Classe classe;

    @ManyToOne(optional = false)
    @NotNull
    private UserAvis user;

    @ManyToOne
    private Question question;


    public Evaluation(String eData, Salle salle, Cours cours, Module module, Classe classe, UserAvis user,
			Question question) {
		super();
		this.eData = eData;
		this.salle = salle;
		this.cours = cours;
		this.module = module;
		this.classe = classe;
		this.user = user;
		this.question = question;
	}

	public Evaluation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getEvaluationId() {
		return evaluationId;
	}

	public void setEvaluationId(Long evaluationId) {
		this.evaluationId = evaluationId;
	}

	public String geteData() {
        return eData;
    }

    public Evaluation eData(String eData) {
        this.eData = eData;
        return this;
    }

    public void seteData(String eData) {
        this.eData = eData;
    }

    public Salle getSalle() {
        return salle;
    }

    public Evaluation salle(Salle salle) {
        this.salle = salle;
        return this;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public Cours getCours() {
        return cours;
    }

    public Evaluation cours(Cours cours) {
        this.cours = cours;
        return this;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public Module getModule() {
        return module;
    }

    public Evaluation module(Module module) {
        this.module = module;
        return this;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Classe getClasse() {
        return classe;
    }

    public Evaluation classe(Classe classe) {
        this.classe = classe;
        return this;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public UserAvis getUser() {
        return user;
    }

    public Evaluation user(UserAvis userAvis) {
        this.user = userAvis;
        return this;
    }

    public void setUser(UserAvis userAvis) {
        this.user = userAvis;
    }

    public Question getQuestion() {
        return question;
    }

    public Evaluation question(Question question) {
        this.question = question;
        return this;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Evaluation evaluation = (Evaluation) o;
        if (evaluation.evaluationId == null || evaluationId == null) {
            return false;
        }
        return Objects.equals(evaluationId, evaluation.evaluationId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(evaluationId);
    }

    @Override
    public String toString() {
        return "Evaluation{" +
            "id=" + evaluationId +
            ", evaluationId='" + evaluationId + "'" +
            ", eData='" + eData + "'" +
            '}';
    }
}
