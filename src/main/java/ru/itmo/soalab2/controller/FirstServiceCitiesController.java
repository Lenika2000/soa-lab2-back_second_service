package ru.itmo.soalab2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.naming.NamingException;

@RestController
@RequestMapping("/api/cities")
public class FirstServiceCitiesController {

    private final RestTemplate restTemplate;
    @Value("${haproxy.service1.url}")
    private String haproxyUrl = "";

    public FirstServiceCitiesController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping
    ResponseEntity<?> getAllCities(RequestEntity<?> requestEntity) {
        return sendRequest(requestEntity, "?" + requestEntity.getUrl().getQuery());
    }

    @PostMapping
    ResponseEntity<?> addCity(RequestEntity<?> requestEntity)  {
        return sendRequest(requestEntity, "");
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<?> updateCity(@PathVariable long id, RequestEntity<?> requestEntity) {
        return sendRequest(requestEntity, "/" + id);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> deleteCity(@PathVariable long id, RequestEntity<?> requestEntity)  {
        return sendRequest(requestEntity, "/" + id);
    }

    @GetMapping(params = "byname")
    ResponseEntity<?> getCitiesByName(@RequestParam String name, RequestEntity<?> requestEntity)  {
        return sendRequest(requestEntity, "?byname=" + name);
    }

    @GetMapping(params = "meters-above-sea-level")
    ResponseEntity<?> getCitiesByMetersAboveSeaLevel(@RequestParam(name = "meters-above-sea-level", defaultValue = "0") int metersAboveSeaLevel, RequestEntity<?> requestEntity) throws NamingException {
        return sendRequest(requestEntity, "?meters-above-sea-level=" + metersAboveSeaLevel);
    }

    @GetMapping(value = "/meters-above-sea-level")
    ResponseEntity<?> getUniqueMetersAboveSeaLevel(RequestEntity<?> requestEntity) {
        return sendRequest(requestEntity, "/meters-above-sea-level");
    }

    private ResponseEntity<?> sendRequest(RequestEntity<?> requestEntity, String url){
        ResponseEntity<?> response = restTemplate.exchange(haproxyUrl + url, requestEntity.getMethod(), requestEntity, String.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}
