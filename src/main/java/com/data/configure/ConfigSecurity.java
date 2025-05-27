package com.data.configure;

import com.data.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfigSecurity {
    @Autowired
    private AccountServiceImpl accountService;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/createAccount").permitAll()
                        .requestMatchers("/accounts/**").hasRole("ADMIN")
                        .requestMatchers("/updateAccount/**").hasRole("ADMIN")
                        .requestMatchers("/deleteAccount/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic();

        return http.build();
    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(accountService).passwordEncoder(new BCryptPasswordEncoder());
//    }

}
