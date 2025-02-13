package com.kasikornline.assignment.app.common.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CorsConfigTest {

    private CorsConfig corsConfig;

    @BeforeEach
    public void setUp() {
        corsConfig = new CorsConfig();
    }

    @Test
    public void testCorsFilterBean() {
        CorsFilter corsFilter = corsConfig.corsFilter();
        assertNotNull(corsFilter, "CorsFilter bean should not be null");
    }

    @Test
    public void testCorsConfigurationSourceBean() {
        UrlBasedCorsConfigurationSource source = corsConfig.corsConfigurationSource();
        assertNotNull(source, "UrlBasedCorsConfigurationSource bean should not be null");

        CorsConfiguration config = source.getCorsConfigurations().get("/**");
        assertNotNull(config, "CorsConfiguration should be registered for /**");

        assertTrue(config.getAllowCredentials(), "AllowCredentials should be true");
        assertTrue(config.getAllowedOriginPatterns().contains("*"), "AllowedOriginPatterns should contain '*'");
        assertTrue(config.getAllowedHeaders().contains("*"), "AllowedHeaders should contain '*'");
        assertTrue(config.getAllowedMethods().contains("*"), "AllowedMethods should contain '*'");
    }
}
