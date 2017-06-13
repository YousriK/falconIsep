package com.falcon.avisep.security.exception;

import org.springframework.security.core.AuthenticationException;

public class MissingAuthorisation extends AuthenticationException
{
    public MissingAuthorisation()
    {
        super("Header Authorisation Not Found");
    }
}
