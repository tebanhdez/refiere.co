package co.refiere.resources.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.refiere.services.AuthenticationService;

public class AuthenticationFilter implements javax.servlet.Filter {
    public static final String AUTHENTICATION_HEADER = "Authorization";
    private List<String> excludedPaths = new ArrayList<String>();
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {

        String path = ((HttpServletRequest) request).getRequestURI();
        //If the url is one of excluded paths, then just continue with next filter
        if (this.excludedPaths.contains(path)) {
            filterChain.doFilter(request, response); 
            return;
        }
        
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String authCredentials = httpServletRequest
                    .getHeader(AUTHENTICATION_HEADER);

            // better injected
            AuthenticationService authenticationService = new AuthenticationService();

            boolean authenticationStatus = authenticationService
                    .authenticate(authCredentials);

            if (authenticationStatus) {
                filterChain.doFilter(request, response);
            } else {
                if (response instanceof HttpServletResponse) {
                    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                    httpServletResponse
                            .setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.excludedPaths = Arrays.asList(config.getInitParameter("excludedPaths").split(","));
    }
}
