//package com.falcon.avisep.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//import com.falcon.avisep.security.TokenAuthenticationFilter;
//import com.falcon.avisep.security.TokenAuthenticationProvider;
//
//@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
//
//	@Autowired
//	private TokenAuthenticationProvider tokenAuthenticationProvider;
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(tokenAuthenticationProvider);
//	}
//
//	@Bean
//	public TokenAuthenticationFilter authenticationTokenFilter() throws Exception {
//		return new TokenAuthenticationFilter();
//	}
//	@Autowired
//	  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//	    auth
//	      .inMemoryAuthentication()
//	        .withUser("teacher")  // #1
//	          .password("falcon")
//	          .roles("ETEACHER")
//	          .and()
//	        .withUser("admin") // #2
//	          .password("password")
//	          .roles("ADMIN","USER");
//	  }
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		http
//		.csrf().disable()
//		.sessionManagement()
//		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		.and()
//		.addFilterBefore(authenticationTokenFilter(), BasicAuthenticationFilter.class).authorizeRequests();
//		http.authorizeRequests()
//        .antMatchers("/welcomeA/**").hasRole("ADMIN") // #6
//        .anyRequest().authenticated() // 7
//        .and()
//    .formLogin()  // #8
//        .loginPage("/login") // #9
//        .permitAll(); // #5
//	}
//	@Override
//	  public void configure(WebSecurity web) throws Exception {
//	    web
//	      .ignoring()
//	         .antMatchers("/resources/**"); // #3
//	  }
//
//}
