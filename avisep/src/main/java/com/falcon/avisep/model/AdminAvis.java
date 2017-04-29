package com.falcon.avisep.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A AdminAvis.
 */
@Entity
@Table(name = "admin_avis")
public class AdminAvis implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long admin_id;

    @OneToMany(mappedBy = "admin")
    @JsonIgnore
    private Set<Form> forms = new HashSet<Form>();
    
    
    
    public AdminAvis() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminAvis(Set<Form> forms) {
		super();
		this.forms = forms;
	}

	public Long getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Long id) {
        this.admin_id = id;
    }

    public Set<Form> getForms() {
        return forms;
    }

    public AdminAvis forms(Set<Form> forms) {
        this.forms = forms;
        return this;
    }

    public AdminAvis addForm(Form form) {
        this.forms.add(form);
        form.setAdmin(this);
        return this;
    }

    public AdminAvis removeForm(Form form) {
        this.forms.remove(form);
        form.setAdmin(null);
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
        AdminAvis adminAvis = (AdminAvis) o;
        if (adminAvis.admin_id == null || admin_id == null) {
            return false;
        }
        return Objects.equals(admin_id, adminAvis.admin_id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(admin_id);
    }

	@Override
	public String toString() {
		return "AdminAvis [admin_id=" + admin_id + ", forms=" + forms + "]";
	}

    
}
