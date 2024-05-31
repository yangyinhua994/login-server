package com.example.filter;

import com.example.config.SecurityConfig;
import com.example.service.CustomUserDetailsService;
import com.example.utils.CheckUtil;
import com.example.utils.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserDetailsService userDetailsService;

    private final AntPathMatcher matcher = new AntPathMatcher();


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, CustomUserDetailsService userDetailsService) {
        super(authenticationManager);
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String requestURI = request.getRequestURI();
        if (isUnsecuredPath(requestURI)) {
            chain.doFilter(request, response);
            return;
        }
        String authorizationHeader = request.getHeader("Authorization");
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            String jwtToken = authorizationHeader.substring(7);
            try {
                Long userId = jwtTokenUtil.extractUserId(jwtToken);
                String phoneNumber = jwtTokenUtil.extractUsername(jwtToken);
                if (StringUtils.hasText(phoneNumber) && !CheckUtil.isEmpty(userId) && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailsService.loadUserById(userId, phoneNumber);
                    if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            } catch (ExpiredJwtException ex) {
                response.setCharacterEncoding("UTF-8");
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.getWriter().write("token已过期");
                return;
            } catch (Exception ex) {
                response.setCharacterEncoding("UTF-8");
                response.sendError(HttpStatus.FORBIDDEN.value(), ex.getMessage());
                return;
            }
        }

        chain.doFilter(request, response);
    }

    private boolean isUnsecuredPath(String requestURI) {
        for (String path : SecurityConfig.UNSECURED_PATHS) {
            if (requestURI.equals(path) || matcher.match(path, requestURI)) {
                return true;
            }
        }
        return false;
    }


}
