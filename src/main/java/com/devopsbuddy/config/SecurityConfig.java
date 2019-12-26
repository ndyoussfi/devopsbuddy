package com.devopsbuddy.config;

import com.devopsbuddy.backend.service.UserSecurityService;
import com.devopsbuddy.web.controllers.ForgotMyPasswordController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{



    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private Environment env; // for jpa h2 console

    /** The encryption SALT*/
    private static String SALT = "asjsreiweojgcjzljhsjdvdffkhd";

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
    }

    /** Public URLs*/
    private static final String[] PUBLIC_MATCHERS = {
            "/webjars/**", // webjars for all libraries that we have setup
            "/css/**",     // all static contents
            "/js/**",
            "/images/**",
            "/",           // home page
            "/about/**",    // about page
            "/contact/**", // contact us page
            "/error/**/*", // errors
            "/console/**",
            ForgotMyPasswordController.FORGOT_PASSWORD_URL_MAPPING,
            ForgotMyPasswordController.CHANGE_PASSWORD_PATH
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // gets list of all active profiles from the environment
        // disable the following options only when developing
        List<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        if (activeProfiles.contains("dev")){ // if running with dev profile
            http.csrf().disable(); // disable csrf
            http.headers().frameOptions().disable(); // disable frameoptions from headers
        }

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
//        auth
//                .inMemoryAuthentication()
//                .withUser("user").password("password")
//                .roles("USER");
        auth
            .userDetailsService(userSecurityService)
            .passwordEncoder(passwordEncoder());
    }
}
