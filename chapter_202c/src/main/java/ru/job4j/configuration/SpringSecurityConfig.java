package ru.job4j.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * Spring data framework configuration.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser("user").password("user").roles("user");
//        auth.jdbcAuthentication()
//                .dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder())//.passwordEncoder(NoOpPasswordEncoder.getInstance())
//                .usersByUsernameQuery("select user.username as username, user.password as password, TRUE from user where user.username=?")
//                .authoritiesByUsernameQuery("select user.username as username, user.username as authority from user where user.username=?");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
//                .antMatchers("/account").permitAll()
                .and().formLogin().defaultSuccessUrl("/account")//.successHandler()
                .and().httpBasic()
                .and().csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/account");
    }

}