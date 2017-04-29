package com.falcon.avisep.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.falcon.avisep.enumeration.RoleAvis;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A UserAvis.
 */
@Entity
@Table(name = "user_avis")
public class UserAvis implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "login")
    private String login;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "email")
    private String email;

    @Column(name = "passwd")
    private String passwd;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_avis")
    private RoleAvis roleAvis;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Evaluation> evaluations = new HashSet<Evaluation>();

    @ManyToMany
    @JoinTable(name = "user_avis_classe",
               joinColumns = @JoinColumn(name="user_avis_userId", referencedColumnName="userId"),
               inverseJoinColumns = @JoinColumn(name="classes_classeId", referencedColumnName="classeId"))
    private Set<Classe> classes = new HashSet<Classe>();

    
    public UserAvis() {
		super();
		// TODO Auto-generated constructor stub
	}
    public UserAvis(String lastName, String login, String firstName, String email, String passwd, RoleAvis roleAvis,
			Set<Evaluation> evaluations, Set<Classe> classes) {
		super();
		this.lastName = lastName;
		this.login = login;
		this.firstName = firstName;
		this.email = email;
		this.passwd = passwd;
		this.roleAvis = roleAvis;
		this.evaluations = evaluations;
		this.classes = classes;
	}
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLastName() {
        return lastName;
    }

    public UserAvis lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public UserAvis login(String login) {
        this.login = login;
        return this;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserAvis firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public UserAvis email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public UserAvis passwd(String passwd) {
        this.passwd = passwd;
        return this;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public RoleAvis getRoleAvis() {
        return roleAvis;
    }

    public UserAvis roleAvis(RoleAvis roleAvis) {
        this.roleAvis = roleAvis;
        return this;
    }

    public void setRoleAvis(RoleAvis roleAvis) {
        this.roleAvis = roleAvis;
    }

    public Set<Evaluation> getEvaluations() {
        return evaluations;
    }

    public UserAvis evaluations(Set<Evaluation> evaluations) {
        this.evaluations = evaluations;
        return this;
    }

    public UserAvis addEvaluation(Evaluation evaluation) {
        this.evaluations.add(evaluation);
        evaluation.setUser(this);
        return this;
    }

    public UserAvis removeEvaluation(Evaluation evaluation) {
        this.evaluations.remove(evaluation);
        evaluation.setUser(null);
        return this;
    }

    public void setEvaluations(Set<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public Set<Classe> getClasses() {
        return classes;
    }

    public UserAvis classes(Set<Classe> classes) {
        this.classes = classes;
        return this;
    }

    public UserAvis addClasse(Classe classe) {
        this.classes.add(classe);
        classe.getUserAvis().add(this);
        return this;
    }

    public UserAvis removeClasse(Classe classe) {
        this.classes.remove(classe);
        classe.getUserAvis().remove(this);
        return this;
    }

    public void setClasses(Set<Classe> classes) {
        this.classes = classes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserAvis userAvis = (UserAvis) o;
        if (userAvis.userId == null || userId == null) {
            return false;
        }
        return Objects.equals(userId, userAvis.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId);
    }

    @Override
    public String toString() {
        return "UserAvis{" +
            "id=" + userId +
            ", lastName='" + lastName + "'" +
            ", login='" + login + "'" +
            ", firstName='" + firstName + "'" +
            ", email='" + email + "'" +
            ", passwd='" + passwd + "'" +
            ", roleAvis='" + roleAvis + "'" +
            '}';
    }
}
