package com.MovieBookingApp.MovieBookingApp.Filter;

import com.MovieBookingApp.MovieBookingApp.Execption.SampleException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
public class JWTFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        final String requestTokenHeader = request.getHeader("Authorization");
        System.out.println(requestTokenHeader+"-----------------------+++");

        String username = null;
        String jwtToken = null;




        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

            //yes
            jwtToken = requestTokenHeader.substring(7);
        System.out.println(jwtToken);

//            jwtToken = requestTokenHeader.substring(7);


            try {
            Claims claims = Jwts.parser().setSigningKey("secret key").parseClaimsJws(jwtToken).getBody();

            request.setAttribute("username", claims);
         System.out.println( request.getAttribute("username")+"from claimss------======");
        }catch (ExpiredJwtException e){

            throw new ExpiredJwtException(e.getHeader(),e.getClaims(),"JWT Expired");
            // throw new SampleException("JWT Token Expired");
        }


        } else {
            System.out.println("Invalid token , not start with bearer string");
        }

        filterChain.doFilter(request, response);


    }

}
