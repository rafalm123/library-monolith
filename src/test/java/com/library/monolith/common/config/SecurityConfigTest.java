package com.library.monolith.common.config;

import com.library.monolith.common.model.dto.user.UserServiceImplementation;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SecurityConfigTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserServiceImplementation userServiceImplementation;

    @Mock
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @InjectMocks
    private SecurityConfig securityConfig;

    @Test
    public void testConfigureAuth() throws Exception {
        securityConfig.configure(authenticationManagerBuilder);
        verify(authenticationManagerBuilder, times(1)).authenticationProvider(any());
    }

    @Test
    public void testDaoAuthenticationProvider() {
        assertNotNull(securityConfig.daoAuthenticationProvider());
    }

}
