package project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Order(1)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthEntryPoint customAuthEntryPoint;

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    AuthFilter authFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/products").hasAuthority("User")
                .antMatchers("/accounts").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(customAuthEntryPoint);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/accounts/**","/warehouse/**","/categories/**", "/transporters/**", "/commentRating/**", "/order/**", "/roles/**", "/products/**");
    }

}

