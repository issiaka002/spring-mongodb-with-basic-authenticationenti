package ci.inphb.appspringmongo.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig   {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password("{noop}1234").authorities("USER").build(),
                User.withUsername("user2").password("{noop}1234").authorities("USER").build(),
                User.withUsername("superAdmin").password("{noop}1234").authorities("USER", "ADMIN").build()
        );
    }

    @Bean
    public DefaultSecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth->auth.anyRequest().authenticated())
                .sessionManagement(sess->sess.sessionCreationPolicy((SessionCreationPolicy.STATELESS)))
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
