package com.project.lpd.config;


import com.project.lpd.service.UserService;
import com.project.lpd.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    UserService userService;


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**").csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index","/productDetail","/about","/products","/cart","/create_product","/pay","/profile","/register","/news","/help","/login").permitAll()
                .antMatchers("/adminIndex").permitAll()
                .antMatchers("/css/**","/js/**","/images/**","vendors/**").permitAll()
                .antMatchers("/admin").hasAuthority("ADMIN")
                .antMatchers("/user").hasAnyAuthority("USER","ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .loginProcessingUrl("/login_security")
                .failureUrl("/login?error")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/index")
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/error");
    }
}
