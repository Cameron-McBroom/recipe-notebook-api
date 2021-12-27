package returnevolved.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import returnevolved.dto.ResponseError;
import returnevolved.exception.CustomException;
import org.springframework.web.filter.OncePerRequestFilter;

// We should use OncePerRequestFilter since we are doing a database call, there is no point in doing this more than once
public class JwtTokenFilter extends OncePerRequestFilter {

  private JwtTokenProvider jwtTokenProvider;

  public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
    String token = jwtTokenProvider.resolveToken(httpServletRequest);
    try {
      if (jwtTokenProvider.validateToken(token)) {
        Authentication auth = jwtTokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    } catch (CustomException ex) {
      //this is very important, since it guarantees the user is not authenticated at all
      SecurityContextHolder.clearContext();
      var response = ResponseError.fromCustomEx(ex);
      httpServletResponse.setStatus(ex.getHttpStatus().value());
      httpServletResponse.setContentType("application/json");
      httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(response));
      return;
    }

    filterChain.doFilter(httpServletRequest, httpServletResponse);

  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    String path = request.getRequestURI();
    return "/users/signin".equals(path) || "/users/signup".equals(path);
  }

}
