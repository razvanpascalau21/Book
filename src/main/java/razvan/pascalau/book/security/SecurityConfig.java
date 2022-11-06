package razvan.pascalau.book.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final MyUserDetailsService myUserService;
    private final MyPasswordEncoder passwordEncoder;

    public SecurityConfig(MyUserDetailsService myUserService, MyPasswordEncoder passwordEncoder) {
        this.myUserService = myUserService;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/v1/book/**").hasAnyRole("ROLE_ADMIN","ROLE_USER")
                .antMatchers(HttpMethod.POST,"/api/v1/book/**").hasAnyRole("ROLE_ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/v1/book/**").hasAnyRole("ROLE_ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/v1/book/**").hasAnyRole("ROLE_ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder.passwordEncoder());
        provider.setUserDetailsService(myUserService);
        return provider;
    }
}
