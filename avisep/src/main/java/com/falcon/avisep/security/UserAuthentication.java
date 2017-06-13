package com.falcon.avisep.security;

import java.util.List;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class UserAuthentication extends AbstractAuthenticationToken
{

    private String login;
    private String password;

    public UserAuthentication(List<GrantedAuthority> authorities,String login,String password)
    {
        super(authorities);
        this.login = login;
        this.password = password;
    }

    @Override
    public Object getCredentials()
    {
        return password;
    }

    @Override
    public Object getPrincipal()
    {
        return login;
    }
}
