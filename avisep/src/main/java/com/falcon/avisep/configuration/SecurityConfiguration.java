package com.falcon.avisep.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.falcon.avisep.security.AuthenticationEntryError;
import com.falcon.avisep.security.AuthenticationSuccess;
import com.falcon.avisep.security.AuthenticationTokenFilter;
import com.falcon.avisep.security.UserProvider;

@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    @Autowired
    private CorsConfig corsConfig;

    @Autowired
    private UserProvider provider;

    @Autowired
    private AuthenticationEntryError entryError;

    

    @Bean
    public AuthenticationTokenFilter authenticationTokenFilter() throws Exception
    {
        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
        authenticationTokenFilter.setAuthenticationManager(authenticationManager());
        authenticationTokenFilter.setAuthenticationSuccessHandler(new AuthenticationSuccess());
        return authenticationTokenFilter;
    }

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.ignoring().antMatchers("/", "/resources/**", "/static/**", "/public/**"
                , "/*.html", "/**/*.html" ,"/**/*.css","/**/*.js","/**/*.png","/**/*.jpg");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
        .csrf().disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(authenticationTokenFilter(), BasicAuthenticationFilter.class)

        // Autorisation de se connecter
        .authorizeRequests()
        .antMatchers("/login/**").permitAll();


        // Toutes les autres requetes necessitent une authentification
       // .anyRequest().authenticated();
    }
}
