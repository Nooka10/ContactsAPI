package ch.benoitschopfer.configuration.JWT;

import ch.benoitschopfer.controller.AuthenticationApiController;
import ch.benoitschopfer.model.other.LoginRequest;
import ch.benoitschopfer.service.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

  @Autowired
  AuthenticationApiController authenticationApiController;

  @Autowired
  private UserDetailsServiceImpl jwtUserDetailsService;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
    String headerAuth = request.getHeader("Authorization");

    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
      String jwt = headerAuth.substring(7);
      if (jwtTokenUtil.validateToken(jwt)) {
        try {
          String email = jwtTokenUtil.getEmailFromToken(jwt);
          UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(email);
          UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities());
          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

          SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (IllegalArgumentException e) {
          logger.error("Unable to get JWT Token: {}", e);
        } catch (ExpiredJwtException e) {
          logger.error("JWT Token has expired: {}", e);
        }
      }
    } else if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Basic ")){
      String basicAuth = headerAuth.substring(6);
      String decoded = new String(Base64.getDecoder().decode(basicAuth));
      String[] loginInfos = decoded.split(":"); // [email, password]

      LoginRequest loginRequest = new LoginRequest(loginInfos[0], loginInfos[1]);

      try {
        authenticationApiController.login(loginRequest);
      } catch (Exception e){
        logger.error("Unable to login. Wrong username or password: {}", e);
      }
    }

    chain.doFilter(request, response);
  }
}
