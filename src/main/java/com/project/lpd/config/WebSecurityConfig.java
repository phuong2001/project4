package com.project.lpd.config;


import com.project.lpd.exception.LoggingAccessDeniedHandler;
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
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    UserService userService;

    @Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;


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
                .antMatchers("/", "/index", "/productDetail", "/about", "/products", "/cart", "/create_product",
                        "/pay", "/register", "/news", "/help", "/adminIndex", "/list", "/productdetail", "/charge", "/profile", "/success",
                        "/sellerproduct", "/IndexSearch", "/list_product_category").permitAll()
                .antMatchers("/css/**", "/js/**", "/images/**", "vendors/**","static/**").permitAll()
                .antMatchers("/listrole", "/listnew", "/updatenew", "/createnew", "/deletenew","/updaterole","/createBanner","/updateBanner","/addfund").hasAnyAuthority("ADMIN")
                .antMatchers("/createproduct", "/userIndex").hasAnyAuthority("USER")
                .antMatchers("/listProductUser", "/updateProductUser", "/createProductUser", "/deleteProductUser", "/listOrderProduct", "/adminIndex").hasAnyAuthority("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/logout")
                .loginProcessingUrl("/login")
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/index")
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/login?logout=true").permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }
}
