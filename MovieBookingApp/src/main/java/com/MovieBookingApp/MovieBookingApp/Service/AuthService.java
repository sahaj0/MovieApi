package com.MovieBookingApp.MovieBookingApp.Service;


import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class AuthService {

   private String baseUrl ="http://localhost:8083/auth/v1";
//   private String baseUrl="http://100.22.13.200:8083/auth/v1";
    private RestTemplate restTemplate = new RestTemplate();

    public Map<String, String> validateToken(String token) {
        HttpEntity<String> entity = getHttpEntityWithHeaders(token);
        try {
            ResponseEntity<Map<String, String>> response =
                    restTemplate.exchange
                            (baseUrl + "/validate", HttpMethod.POST,
                                    entity, new ParameterizedTypeReference<Map<String, String>>() {
            });
            System.out.println(response.getBody()+"from*********");
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                throw new RuntimeException("Failed to validate token: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("connection refused: " + e.getMessage());
        }
    }

    private HttpEntity<String> getHttpEntityWithHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        return new HttpEntity<>("", headers);
    }
}
