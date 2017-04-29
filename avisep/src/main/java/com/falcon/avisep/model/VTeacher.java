package com.falcon.avisep.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * A VTeacher.
 */
@Entity
@Table(name = "v_teacher")
public class VTeacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long vtId;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private Form form;


    public VTeacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VTeacher(Form form) {
		super();
		this.form = form;
	}

	public Long getVtId() {
		return vtId;
	}

	public void setVtId(Long vtId) {
		this.vtId = vtId;
	}

	public Form getForm() {
        return form;
    }

    public VTeacher form(Form form) {
        this.form = form;
        return this;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VTeacher vTeacher = (VTeacher) o;
        if (vTeacher.vtId == null || vtId == null) {
            return false;
        }
        return Objects.equals(vtId, vTeacher.vtId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(vtId);
    }

    @Override
    public String toString() {
        return "VTeacher{" +
            "id=" + vtId +
            ", vtId='" + vtId + "'" +
            '}';
    }
}
