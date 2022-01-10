package ru.itmo.soalab2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.itmo.soalab2.model.ErrorListWrap;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.Map;

@RestController
@RequestMapping("/api/cities")
public class FirstServiceCitiesController {

    private final RestTemplate restTemplate;
    @Value("${service1.url}")
    private String serviceUrl = "";
    @Value("${soap.service.url}")
    private String soapServiceUrl = "";

    public FirstServiceCitiesController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping
    ResponseEntity<?> getAllCities(HttpServletRequest httpServletRequest, RequestEntity<?> requestEntity) {
        Map<String, String[]> requestParameterMap = httpServletRequest.getParameterMap();
        CityRequestParams filterParams = new CityRequestParams(requestParameterMap);
        RequestEntity<?> changedRequestEntity = new RequestEntity(filterParams, requestEntity.getHeaders(), HttpMethod.POST, requestEntity.getUrl());
        return sendRequestToSoapService(changedRequestEntity, "/filter?" + requestEntity.getUrl().getQuery());
    }

    @PostMapping
    ResponseEntity<?> addCity(RequestEntity<?> requestEntity) {
        return sendRequestToSoapService(requestEntity, "");
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<?> updateCity(@PathVariable Integer id, RequestEntity<?> requestEntity) {
        return sendRequestToSoapService(requestEntity, "/" + id);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> deleteCity(@PathVariable long id, RequestEntity<?> requestEntity) {
        return sendRequestToSoapService(requestEntity, "/" + id);
    }

    @GetMapping(value = "/filter", params = "byname")
    ResponseEntity<?> getCitiesByName(@RequestParam(name = "byname") String name, RequestEntity<?> requestEntity) {
        return sendRequestToSoapService(requestEntity, "?byname=" + name);
    }

    @GetMapping(value = "/filter", params = "meters-above-sea-level")
    ResponseEntity<?> getCitiesByMetersAboveSeaLevel(@RequestParam(name = "meters-above-sea-level", defaultValue = "0") int metersAboveSeaLevel, RequestEntity<?> requestEntity) throws NamingException {
        return sendRequestToSoapService(requestEntity, "/filter?meters-above-sea-level=" + metersAboveSeaLevel);
    }

    @GetMapping(value = "/meters-above-sea-level")
    ResponseEntity<?> getUniqueMetersAboveSeaLevel(RequestEntity<?> requestEntity) {
        return sendRequestToSoapService(requestEntity, "/meters-above-sea-level");
    }

//    private ResponseEntity<?> sendRequest(RequestEntity<?> requestEntity, String url){
//        try {
//            ResponseEntity<?> response = restTemplate.exchange(serviceUrl + url, requestEntity.getMethod(), requestEntity, String.class);
//            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
//        }
//        catch (final HttpClientErrorException e) {
//            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
//        }
//    }

    private ResponseEntity<?> sendRequestToSoapService(RequestEntity<?> requestEntity, String url) {
        try {
            ResponseEntity<?> response = restTemplate.exchange(soapServiceUrl + url, requestEntity.getMethod(), requestEntity, String.class);
            String xml = response.getBody().toString();
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (final HttpClientErrorException e) {
            if (e.getRawStatusCode() == 400) {
                String errorListXml = e.getResponseBodyAsString().replace("<detail><ns2:validateFieldsException xmlns:ns2=\\\"http://controller.soalab2.itmo.ru/\\\">", "")
                        .replace("<message>Validate error</message></ns2:validateFieldsException></detail>", "").replaceAll("\\\\", "");
                ;
                errorListXml = errorListXml.substring(1, errorListXml.length() - 1);
                StringReader reader = new StringReader(errorListXml);
                JAXBContext jaxbContext;
                try {
                    jaxbContext = JAXBContext.newInstance(ErrorListWrap.class);
                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    ErrorListWrap errorListWrap = (ErrorListWrap) jaxbUnmarshaller.unmarshal(reader);
                    return ResponseEntity.status(e.getStatusCode()).body(errorListWrap);
                } catch (JAXBException ex) {
                    ex.printStackTrace();
                }
            }
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }
}
