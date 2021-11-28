package ru.itmo.soalab2.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.soalab2.model.OperationResponse;
import ru.itmo.soalab2.repo.CityRepository;
import ru.itmo.soalab2.services.CityService;

@RestController
@RequestMapping("/api/route")
public class SecondServiceCitiesController {

    private CityService cityService;

    SecondServiceCitiesController(CityRepository cityRepository) {
        this.cityService = new CityService(cityRepository);
    }

    @GetMapping(value = "/calculate/to-largest")
    ResponseEntity<?> calculateToLargest(@RequestParam String from) {
        if (from == null) return ResponseEntity.status(400).body(new OperationResponse(0L, "From city is not specified"));
        return cityService.calculateToLargest(from);
    }

    @GetMapping(value = "/calculate/to-min-populated")
    ResponseEntity<?> calculateToMinPopulated() {
        return cityService.calculateToMinPopulated();
    }
}
