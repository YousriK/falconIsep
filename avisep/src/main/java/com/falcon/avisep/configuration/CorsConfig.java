package com.falcon.avisep.configuration;

import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;

@Component
public class CorsConfig implements CorsConfigurationSource
{
    @Override
    public CorsConfiguration getCorsConfiguration(HttpServletRequest request)
    {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addAllowedOrigin("*");
        return config;
    }
}
