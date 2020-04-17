package org.itstep.config;


import org.itstep.service.security.CustomAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@ComponentScan("org.itstep")
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

    final AuthenticationProvider customAuthenticationProvider;

    final DataSource dataSource;

    public WebSecurity(AuthenticationProvider customAuthenticationProvider, DataSource dataSource) {
        this.customAuthenticationProvider = customAuthenticationProvider;
        this.dataSource = dataSource;
    }

    @Override
    public void configure(org.springframework.security.config.annotation.web.builders.WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers("/students/register/**").access("isAnonymous()")



                                .antMatchers("/students/create/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
                                .antMatchers("/groups/create/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
                                .antMatchers("/teachers/create/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")

                                .antMatchers("/students/update/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
                                .antMatchers("/groups/update/**").hasAnyRole("ROLE_ADMIN", "ROLE_TEACHER")
                                .antMatchers("/teachers/update/**").hasAnyRole("ROLE_ADMIN", "ROLE_TEACHER")

                                .antMatchers("/students/delete/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
                                .antMatchers("/groups/delete/**").hasAnyRole("ROLE_ADMIN", "TEACHER")
                                .antMatchers("/teachers/delete/**").hasAnyRole("ROLE_ADMIN", "TEACHER")

                                .antMatchers("/students/**").access("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_TEACHER')")
                                .antMatchers("/groups/**").access("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_TEACHER')")
                                .antMatchers("/teachers/**").access("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_TEACHER')")


                                .antMatchers("/**").permitAll()

                )

                .rememberMe(rememberMe ->
                        rememberMe
                                .key("rememberMe").rememberMeParameter("rememberMe")
                                .tokenRepository(tokenRepository())
                )
                .formLogin(loginForm ->
                        loginForm
                                .loginPage("/")
                                .loginProcessingUrl("/login")
                                .usernameParameter("custom_username")
                                .passwordParameter("custom_password")
                                .failureForwardUrl("/login?error=notentry")
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/")
                                .deleteCookies()
                        .permitAll()
                );
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        jdbcTokenRepositoryImpl.setDataSource(dataSource);
        return jdbcTokenRepositoryImpl;
    }
}
