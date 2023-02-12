package org.goodgoodgood.freetubeexservice.config.auth

import org.goodgoodgood.freetubeexservice.domain.user.Role
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        http
            .csrf().disable()
            .headers().frameOptions().disable()
            .and()
                .authorizeRequests()
                .mvcMatchers("/").permitAll()
                .mvcMatchers("/api/hello").authenticated()
                .mvcMatchers("/api/**").hasRole(Role.USER.name)
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginProcessingUrl("/api/login")

        return http.build()
    }
}