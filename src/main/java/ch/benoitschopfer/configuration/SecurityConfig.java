package ch.benoitschopfer.configuration;

import ch.benoitschopfer.configuration.JWT.JwtAuthenticationEntryPoint;
import ch.benoitschopfer.configuration.JWT.JwtRequestFilter;
import ch.benoitschopfer.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String[] AUTH_LIST = {
    "/",
    "/api-docs",
    "/v2/api-docs",
    "/configuration/ui",
    "/swagger-resources",
    "/configuration/security",
    "/swagger-ui.html",
    "/webjars/**",
    "/**/*.html",
    "/**/*.css",
    "/**/*.js"
  };

  @Autowired
  private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  @Autowired
  private UserDetailsServiceImpl jwtUserDetailsService;

  @Autowired
  private JwtRequestFilter jwtRequestFilter;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    // configure AuthenticationManager so that it knows from where to load
    // user and use BCryptPasswordEncoder for matching credential
    auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    // Disable CSRF
    httpSecurity.cors().and().csrf().disable()
      // We don't need sessions to be created.
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      // Reject every unauthenticated request and send error code 401.
      .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
      .and().authorizeRequests().antMatchers(AUTH_LIST).permitAll()
      .antMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
      .antMatchers("/api/secured/**").authenticated()
      // .anyRequest().authenticated()
      // Add a filter to validate the tokens with every request
      .and().addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
