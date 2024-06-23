package com.youplay.reservation.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youplay.reservation.models.Host;
import com.youplay.reservation.models.modelsDTO.ReservationDTO;
import com.youplay.reservation.models.modelsDTO.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GizmoApiService {

    @Value("${reservation.gizmo_url}")
    String GIZMO_URL;
    private final RestTemplate restTemplate;
    private final HttpHeaderService httpHeaderService;
    private final ObjectMapper objectMapper;

    @Autowired
    public GizmoApiService(RestTemplate restTemplate, HttpHeaderService httpHeaderService, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.httpHeaderService = httpHeaderService;
        this.objectMapper = objectMapper;
    }

    public List<Host> getPcList() throws IOException {
        String url = GIZMO_URL + "/api/v2.0/hosts";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("Pagination.Limit", 100)
                .queryParam("IsDeleted", false);

        HttpEntity<String> entity = new HttpEntity<>(httpHeaderService.prepareHeaders());
        ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

        byte[] responseBodyBytes = Objects.requireNonNull(response.getBody()).getBytes(StandardCharsets.UTF_8);
        Response responseJson = objectMapper.readValue(responseBodyBytes, Response.class);

        return new ArrayList<>(responseJson.getResult().getData());
    }

    public Response createReservation(ReservationDTO reservationDTO) throws JsonProcessingException {
        String url = GIZMO_URL + "/api/v2.0/reservations";
        String json = objectMapper.writeValueAsString(reservationDTO);
        HttpEntity<String> entity = new HttpEntity<>(json, httpHeaderService.prepareHeaders());
        ResponseEntity<Response> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, Response.class);
        return responseEntity.getBody();
    }

    public void updateReservation(ReservationDTO reservationDTO) throws JsonProcessingException {
        String url = GIZMO_URL + "/api/v2.0/reservations";
        String json = objectMapper.writeValueAsString(reservationDTO);

        HttpEntity<String> entity = new HttpEntity<>(json, httpHeaderService.prepareHeaders());
        restTemplate.exchange(url, HttpMethod.PUT, entity, Response.class);
    }

    public void deleteReservation(Long reservationNumber) {
        String url = GIZMO_URL + "/api/v2.0/reservations/" + reservationNumber;
        HttpEntity<String> entity = new HttpEntity<>(httpHeaderService.prepareHeaders());

        restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
    }
}
