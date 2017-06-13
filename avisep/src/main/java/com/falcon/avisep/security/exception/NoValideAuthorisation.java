package com.falcon.avisep.security.exception;

import org.springframework.security.core.AuthenticationException;

public class NoValideAuthorisation extends AuthenticationException
{
    public NoValideAuthorisation()
    {
        super("Token is Not Valid");
    }
}
