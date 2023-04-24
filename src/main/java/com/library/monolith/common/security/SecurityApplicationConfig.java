package com.library.monolith.common.security;

import com.library.monolith.common.exception.user.UserError;
import com.library.monolith.common.exception.user.UserException;
import com.library.monolith.common.service.user.UserServiceImplementation;
import com.library.monolith.common.repository.user.LibraryUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityApplicationConfig {

    private final JwtAuthEntryPoint authEntryPoint;
    private final LibraryUserRepository libraryUserRepository;
    private final JwtGenerator jwtGenerator;
    private final UserServiceImplementation userServiceImplementation;

    public SecurityApplicationConfig(JwtAuthEntryPoint authEntryPoint,
                                     LibraryUserRepository libraryUserRepository,
                                     JwtGenerator jwtGenerator,
                                     @Lazy UserServiceImplementation userServiceImplementation) {
        this.authEntryPoint = authEntryPoint;
        this.libraryUserRepository = libraryUserRepository;
        this.jwtGenerator = jwtGenerator;
        this.userServiceImplementation = userServiceImplementation;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/","/register_success",
                        "/process_register","/index").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> libraryUserRepository.findByUsername(username).
                orElseThrow(() -> {
                    throw new UserException(UserError.LIBRARY_USER_NOT_FOUND);
                });
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(Collections.singletonList(authenticationProvider));
    }


    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter(jwtGenerator, userServiceImplementation);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}



//                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/auth/authenticate")
//                .failureUrl("/login?error")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .permitAll()
//                .and()
//                .httpBasic().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);



//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/","/register","/register_success",
//                        "/process_register","/index","/auth/login").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .defaultSuccessUrl("/home",true)
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .and()
//                .rememberMe()
//                .tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(21))
//                .key("somethingverysecured")
//                .rememberMeParameter("remember-me")
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"))
//                .clearAuthentication(true)
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID","remember-me")
//                .logoutSuccessUrl("/login");
//    }

//


