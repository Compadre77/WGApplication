package com.webec.WGApplication;

import com.webec.WGApplication.model.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserRepository userRepository;

    public WebSecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/**.css", "/**.png", "/", "/finanzen", "/ämtli", "/einkauf").permitAll()
                .antMatchers("/contacts/add").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin(Customizer.withDefaults());

    }
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        var builder = User.withDefaultPasswordEncoder();

        var sophia = builder
                .username("sophia").password("sophia")
                .roles().build();
        var daniel = builder
                .username("daniel").password("daniel")
                .roles("ADMIN").build();
        var felix = builder
                .username("felix").password("felix")
                .roles().build();

        var sabrina = builder
                .username("sabrina").password("sabrina")
                .roles().build();
        return new InMemoryUserDetailsManager(sophia, daniel, felix, sabrina);
//        return username -> userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

}
