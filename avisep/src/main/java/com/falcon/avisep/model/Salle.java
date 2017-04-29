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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A Salle.
 */
@Entity
@Table(name = "salle")
public class Salle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long salleId;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "salle")
    @JsonIgnore
    private Set<Evaluation> evaluations = new HashSet<Evaluation>();

    @ManyToOne
    private Cours cours;


    public Salle(String name, String location, Set<Evaluation> evaluations, Cours cours) {
		super();
		this.name = name;
		this.location = location;
		this.evaluations = evaluations;
		this.cours = cours;
	}

	public Salle() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getSalleId() {
		return salleId;
	}

	public void setSalleId(Long salleId) {
		this.salleId = salleId;
	}

	public String getName() {
        return name;
    }

    public Salle name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public Salle location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Evaluation> getEvaluations() {
        return evaluations;
    }

    public Salle evaluations(Set<Evaluation> evaluations) {
        this.evaluations = evaluations;
        return this;
    }

    public Salle addEvaluation(Evaluation evaluation) {
        this.evaluations.add(evaluation);
        evaluation.setSalle(this);
        return this;
    }

    public Salle removeEvaluation(Evaluation evaluation) {
        this.evaluations.remove(evaluation);
        evaluation.setSalle(null);
        return this;
    }

    public void setEvaluations(Set<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public Cours getCours() {
        return cours;
    }

    public Salle cours(Cours cours) {
        this.cours = cours;
        return this;
    }

    public void setCours(Cours cours) {
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
        Salle salle = (Salle) o;
        if (salle.salleId == null || salleId == null) {
            return false;
        }
        return Objects.equals(salleId, salle.salleId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(salleId);
    }

    @Override
    public String toString() {
        return "Salle{" +
            "id=" + salleId +
            ", salleId='" + salleId + "'" +
            ", name='" + name + "'" +
            ", location='" + location + "'" +
            '}';
    }
}
