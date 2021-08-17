package com.project.lpd.config;


import com.project.lpd.service.UserService;
import com.project.lpd.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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
                .antMatchers("/", "/index", "/login", "/productDetail", "/about", "/products", "/cart", "/create_product",
                        "/pay","/register", "/news", "/help", "/adminIndex", "/list", "/productdetail", "/charge","/profile","/success").permitAll()
                .antMatchers("/css/**", "/js/**", "/images/**", "vendors/**").permitAll()
                .antMatchers("/adminIndex", "/listrole", "/listnew", "/updatenew", "/createnew", "/deletenew", "/updaterole").hasAnyAuthority("ADMIN")
                .antMatchers("/createproduct","/userIndex").hasAnyAuthority("USER")
                .antMatchers("/listProductUser","/updateProductUser", "/createProductUser", "/deleteProductUser","/listOrderProduct").hasAnyAuthority("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login_security")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/index")
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/error");
    }
}
