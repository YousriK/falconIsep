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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A Module.
 */
@Entity
@Table(name = "module")
public class Module implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long moduleId;
    
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "module")
    @JsonIgnore
    private Set<Evaluation> evaluations = new HashSet<Evaluation>();

    @OneToMany(mappedBy = "module")
    @JsonIgnore
    private Set<Cours> cours = new HashSet<Cours>();


    public Module(String name, String description, Set<Evaluation> evaluations, Set<Cours> cours) {
		super();
		this.name = name;
		this.description = description;
		this.evaluations = evaluations;
		this.cours = cours;
	}

	public Module() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public String getName() {
        return name;
    }

    public Module name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Module description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Evaluation> getEvaluations() {
        return evaluations;
    }

    public Module evaluations(Set<Evaluation> evaluations) {
        this.evaluations = evaluations;
        return this;
    }

    public Module addEvaluation(Evaluation evaluation) {
        this.evaluations.add(evaluation);
        evaluation.setModule(this);
        return this;
    }

    public Module removeEvaluation(Evaluation evaluation) {
        this.evaluations.remove(evaluation);
        evaluation.setModule(null);
        return this;
    }

    public void setEvaluations(Set<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public Set<Cours> getCours() {
        return cours;
    }

    public Module cours(Set<Cours> cours) {
        this.cours = cours;
        return this;
    }

    public Module addCours(Cours cours) {
        this.cours.add(cours);
        cours.setModule(this);
        return this;
    }

    public Module removeCours(Cours cours) {
        this.cours.remove(cours);
        cours.setModule(null);
        return this;
    }

    public void setCours(Set<Cours> cours) {
        this.cours = cours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Module module = (Module) o;
        if (module.moduleId == null || moduleId == null) {
            return false;
        }
        return Objects.equals(moduleId, module.moduleId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(moduleId);
    }

    @Override
    public String toString() {
        return "Module{" +
            "id=" + moduleId +
            ", moduleId='" + moduleId + "'" +
            ", name='" + name + "'" +
            ", description='" + description + "'" +
            '}';
    }
}
