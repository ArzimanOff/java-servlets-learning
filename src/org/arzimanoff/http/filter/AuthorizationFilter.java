package org.arzimanoff.http.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.arzimanoff.http.dto.UserDto;

import java.io.IOException;
import java.util.Set;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    private static final Set<String> PUBLIC_PATH = Set.of("/login", "/registration", "/hello");

    private static final Set<String> ADMIN_PATH = Set.of("/tickets");


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var uri = ((HttpServletRequest) servletRequest).getRequestURI();
        var userLoggedIn = isUserLoggedIn(servletRequest);
        if (userLoggedIn != null) {
            if (userLoggedIn.getRole().name().equals("ADMIN")){
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                if (!isAdminPath(uri)){
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    var prevPage = ((HttpServletRequest) servletRequest).getHeader("referer");
                    ((HttpServletResponse) servletResponse).sendRedirect(prevPage != null ? prevPage : "/login");
                }
            }
        } else {
            if (isPublicPath(uri)){
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                var prevPage = ((HttpServletRequest) servletRequest).getHeader("referer");

                ((HttpServletResponse) servletResponse).sendRedirect(prevPage != null ? prevPage : "/login");
            }
        }

    }

    private UserDto isUserLoggedIn(ServletRequest servletRequest) {
        return (UserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
    }

    private boolean isPublicPath(String uri) {
        return PUBLIC_PATH.stream().anyMatch(uri::startsWith);
    }

    private boolean isAdminPath(String uri) {
        return ADMIN_PATH.stream().anyMatch(uri::startsWith);
    }
}
