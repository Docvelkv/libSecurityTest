package docvel.libSecurityTest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeHttpRequests(registry -> registry
                        .requestMatchers("addBook",
                                "saveBook",
                                "deleteBook",
                                "updateBook",
                                "allIssues",
                                "allReaders").hasAuthority("ADMIN")
                        .requestMatchers("addReader",
                                "saveReader",
                                "deleteReader",
                                "updateReader",
                                "addIssue",
                                "saveIssue",
                                "returnIssue").hasAnyAuthority("READER", "ADMIN")
                        .requestMatchers("library", "allBooks").permitAll())
                .formLogin(Customizer.withDefaults())
                .build();
    }
}
