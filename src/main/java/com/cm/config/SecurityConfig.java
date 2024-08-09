package com.cm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.cm.impls.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfig {

    // user create and login using java code with memory service

    // @Bean
    // public UserDetailsService userDetailsService(){
    // UserDetails user1=User
    // .withDefaultPasswordEncoder()
    // .username("admin123")
    // .password("admin123")
    // .roles("ADMIN","USER")
    // .build();
    // UserDetails user2=User
    // .withDefaultPasswordEncoder()
    // .username("user123")
    // .password("user123")
    // //.roles("ADMIN","USER")
    // .build();
    // var inMemoryUserDetailsManager =new InMemoryUserDetailsManager(user1,user2);
    // return inMemoryUserDetailsManager;
    // }

    @Autowired
    private SecurityCustomUserDetailService userDetailService;

    @Autowired
    private OAuthAuthenticationSuccessHandler handler;

    @Autowired
    private AuthFailureHandler authFailureHandler;

    // configuration of authentication provider for spring security
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // userdetail service ka object
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        // password encoder ka object
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // url konse public rhge aur konse private rhge
        // routess
        httpSecurity.authorizeHttpRequests(authorize -> {
            // authorize.requestMatchers("/home","/register","/services").permitAll();
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });
        // form default login
        // agar hume kuch bhi change krna ho toh yaha aayege login se related
        httpSecurity.formLogin(formlogin -> {
            // login hoga toh loginpage pr
            formlogin.loginPage("/login");
            // pr processing ho aur submit hoga authenicated pr hoga
            formlogin.loginProcessingUrl("/authenticate");
            formlogin.defaultSuccessUrl("/user/profile");
            // formlogin.failureForwardUrl("/login?error=true");
            // formlogin.defaultSuccessUrl("/home");
            formlogin.usernameParameter("email");
            formlogin.passwordParameter("password");
            // if user is not authenticated
            formlogin.failureHandler(new AuthFailureHandler());

        });

        // oauth configuration
        httpSecurity.oauth2Login(oauth -> {
            oauth.loginPage("/login");
            oauth.successHandler(handler);
        });

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.logout(logoutForm -> {
            logoutForm.logoutUrl("/do-logout");
            logoutForm.logoutSuccessUrl("/login?logout=true");
        });

        return httpSecurity.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
