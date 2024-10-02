package com.sunbase.customer;
//importing 
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebFilter("/api/*") // This annotation helps in enabling the filter for specific URL patterns 
public class AuthFilter implements Filter {

    // Constant that use to represent the Authorization header key
    private static final String AUTHENTICATION_HEADER = "Authorization";

    // Its the main method that use to intercepts the requests and responses
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        // Casting to HttpServletRequest and HttpServletResponse for HTTP-specific operations
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Extracting the 'Authorization' header by the help of the request
        String authHeader = httpRequest.getHeader(AUTHENTICATION_HEADER);

        // Checking whether the Authorization header is present or not
        if (authHeader != null) {
            // Decoding Base64 credentials and spliting them into username and password
            String[] credentials = decodeBase64(authHeader.replace("Basic ", ""));
            String username = credentials[0];
            String password = credentials[1];

            // Checking basic authentication : where valid credentials are 'admin' and 'password'
            if ("admin".equals(username) && "password".equals(password)) {
                // Proceed with the filter chain if authentication is successful
                filterChain.doFilter(request, response);
            } else {
                // If the authentication fails, It will return the respond with an HTTP 401 status (Unauthorized)
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.getWriter().println("Unauthorized");
            }
        } else {
            // If the authorization header is missing, It will return the respond with an HTTP 401 status
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().println("Unauthorized");
        }
    }

    // Its an helper method that helps in decoding the Base64 encoded credentials
    private String[] decodeBase64(String encodedCredentials) {
        // Decoding the Base64 string into the bytes
        byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);
        // Converting the decoded bytes again into the String by the help of UTF-8 charset
        String credentials = new String(decodedBytes, StandardCharsets.UTF_8);
        // Spliting the credentials into username and password using ':' as a delimiter
        return credentials.split(":");
    }
}
