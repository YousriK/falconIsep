package com.falcon.avisep.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.GenerationType;

import com.fasterxml.jackson.annotation.JsonIgnore;


@javax.persistence.Entity 
@javax.persistence.Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@javax.persistence.DiscriminatorColumn(name="UserType", discriminatorType=javax.persistence.DiscriminatorType.STRING)

public abstract class UserAvis
{
	@javax.persistence.Column 
	protected String lastName;

	protected String token;
	protected Date tokenExpiration;
    
	 private String ldapId;
	 
	@javax.persistence.Column 
	protected String login;

	 
	@javax.persistence.Column 
	protected String firstName;
	 
	@javax.persistence.Column 
	protected String email;

	@javax.persistence.Column 
	protected String passwd;

	@javax.persistence.Enumerated(javax.persistence.EnumType.STRING) 
//	@javax.persistence.ElementCollection(targetClass = Role.class) 
	protected Role role;


	@javax.persistence.ManyToMany(cascade = javax.persistence.CascadeType.ALL) 
	protected Set<Classe> classe;

	@javax.persistence.ManyToMany(cascade = javax.persistence.CascadeType.ALL) 
	protected Set<Module> module;

	@javax.persistence.OneToMany( fetch = javax.persistence.FetchType.EAGER,mappedBy = "userAvis") 
	protected Set<Form> form;
	
	@javax.persistence.Id 
	@javax.persistence.GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long id;

	public UserAvis(){
		super();
	}
	
	@JsonIgnore
    public String getLdapId() {
        return ldapId;
    }
    public void setLdapId(String ldapId) {
        this.ldapId = ldapId;
    }

	@JsonIgnore
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    @JsonIgnore
    public Date getTokenExpiration() {
        return tokenExpiration;
    }

    public void setTokenExpiration(Date tokenExpiration) {
        this.tokenExpiration = tokenExpiration;
    }
	public Role getRole() {
		return this.role;
	}
	public void setRole(Role myRole) {
		this.role = myRole;
	}
	public void unsetRole() {
		this.role = null;
	}
	public String getLastName() {
		return this.lastName;
	}

	public String getLogin() {
		return this.login;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getEmail() {
		return this.email;
	}
	public String getPasswd() {
		return this.passwd;
	}

	public Set<Classe> getClasse() {
		if(this.classe == null) {
				this.classe = new HashSet<Classe>();
		}
		return (Set<Classe>) this.classe;
	}
	public Set<Module> getModule() {
		if(this.module == null) {
				this.module = new HashSet<Module>();
		}
		return (Set<Module>) this.module;
	}

	public Set<Form> getForm() {
		if(this.form == null) {
				this.form = new HashSet<Form>();
		}
		return (Set<Form>) this.form;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void addAllClasse(Set<Classe> newClasse) {
		if (this.classe == null) {
			this.classe = new HashSet<Classe>();
		}
		for (Classe tmp : newClasse)
			tmp.addUserAvis(this);
		
	}

	public void addAllModule(Set<Module> newModule) {
		if (this.module == null) {
			this.module = new HashSet<Module>();
		}
		for (Module tmp : newModule)
			tmp.addUserAvis(this);
		
	}
	public void addAllForm(Set<Form> newForm) {
		if (this.form == null) {
			this.form = new HashSet<Form>();
		}
		for (Form tmp : newForm)
			tmp.setUserAvis(this);
		
	}
	public void removeAllClasse(Set<Classe> newClasse) {
		if(this.classe == null) {
			return;
		}
		
		this.classe.removeAll(newClasse);
	}

	public void removeAllModule(Set<Module> newModule) {
		if(this.module == null) {
			return;
		}
		
		this.module.removeAll(newModule);
	}

	public void removeAllForm(Set<Form> newForm) {
		if(this.form == null) {
			return;
		}
		
		this.form.removeAll(newForm);
	}
	public void setLastName(String myLastName) {
		this.lastName = myLastName;
	}

	public void setLogin(String myLogin) {
		this.login = myLogin;
	}

	public void setFirstName(String myFirstName) {
		this.firstName = myFirstName;
	}

	public void setEmail(String myEmail) {
		this.email = myEmail;
	}
	public void setPasswd(String myPasswd) {
		this.passwd = myPasswd;
	}


	public void addClasse(Classe newClasse) {
		if(this.classe == null) {
			this.classe = new HashSet<Classe>();
		}
		
		if (this.classe.add(newClasse))
			newClasse.addUserAvis(this);
	}

	public void addModule(Module newModule) {
		if(this.module == null) {
			this.module = new HashSet<Module>();
		}
		
		if (this.module.add(newModule))
			newModule.addUserAvis(this);
	}

	public void addForm(Form newForm) {
		if(this.form == null) {
			this.form = new HashSet<Form>();
		}
		
		if (this.form.add(newForm))
			newForm.basicSetUserAvis(this);
	}

	public void unsetLastName() {
		this.lastName = null;
	}

	public void unsetLogin() {
		this.login = null;
	}
	public void unsetFirstName() {
		this.firstName = null;
	}

	public void unsetEmail() {
		this.email = null;
	}

	public void unsetPasswd() {
		this.passwd = null;
	}

	public void removeClasse(Classe oldClasse) {
		if(this.classe == null)
			return;
		
		if (this.classe.remove(oldClasse))
			oldClasse.removeUserAvis(this);
		
	}
	public void removeModule(Module oldModule) {
		if(this.module == null)
			return;
		
		if (this.module.remove(oldModule))
			oldModule.removeUserAvis(this);
		
	}

	public void removeForm(Form oldForm) {
		if(this.form == null)
			return;
		
		if (this.form.remove(oldForm))
			oldForm.unsetUserAvis();
		
	}

}

