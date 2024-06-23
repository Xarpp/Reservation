package com.youplay.reservation.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class HttpHeaderService {

    @Value("${reservation.gizmo_login}")
    private String GIZMO_LOGIN;
    @Value("${reservation.gizmo_password}")
    private String GIZMO_PASSWORD;

    public HttpHeaders prepareHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String credentials = GIZMO_LOGIN + ":" + GIZMO_PASSWORD;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        headers.add("Authorization", "Basic " + encodedCredentials);

        return headers;
    }
}
