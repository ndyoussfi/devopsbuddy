package com.devopsbuddy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    /** Public URLs*/
    private static final String[] PUBLIC_MATCHERS = {
            "/webjars/**", // webjars for all libraries that we have setup
            "/css/**",     // all static contents
            "/js/**",
            "/images/**",
            "/",           // home page
            "/about/**",    // about page
            "/contact/**", // contact us page
            "/error/**/*" // errors

    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() // authorize all http requests
                .antMatchers(PUBLIC_MATCHERS).permitAll() // dont require auth for these
                .anyRequest().authenticated() // authenticate for everything else
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/payload") // use this form login for auth and direct to payload
                .failureUrl("/login?error").permitAll() // if error, redirect here
                .and()
                .logout().permitAll(); // allow anyone to logout
        //        super.configure(http);
    }

    /** configure global method which accepts Authentication manager builder*/
    // because we have declared springboot security dependency,
    // this bean is made available to us by spring
    // @Autowired annotation helps spring inject this class as method parameter
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password")
                .roles("USER");
    }
}
