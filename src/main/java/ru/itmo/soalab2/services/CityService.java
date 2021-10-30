package ru.itmo.soalab2.services;

import org.springframework.http.ResponseEntity;
import ru.itmo.soalab2.model.City;
import ru.itmo.soalab2.model.CityNameAndRoadLength;
import ru.itmo.soalab2.model.OperationResponse;
import ru.itmo.soalab2.repo.CityRepository;

public class CityService {
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public ResponseEntity<?> calculateToLargest(String from) {
        City cityWithMaxArea = this.cityRepository.findTopByOrderByAreaDesc();
        if (cityWithMaxArea == null)
            return ResponseEntity.status(404).body(new OperationResponse(0L, "Cannot find city with max area"));
        City currentCity = this.cityRepository.findByName(from);
        if (currentCity == null)
            return ResponseEntity.status(404).body(new OperationResponse(0L, "Cannot find city with name " + from));

        double roadLength = Math.sqrt(Math.pow(cityWithMaxArea.getCoordinates().getX() - currentCity.getCoordinates().getX(), 2) +
                Math.pow(cityWithMaxArea.getCoordinates().getY() - currentCity.getCoordinates().getY(), 2));

        return ResponseEntity.status(200).body(new CityNameAndRoadLength(roadLength, cityWithMaxArea.getName()));
    }

    public ResponseEntity<?> calculateToMinPopulated() {
        City cityWithMinPopulation = this.cityRepository.findTopByOrderByPopulationAsc();
        if (cityWithMinPopulation == null)
            return ResponseEntity.status(404).body(new OperationResponse(0L, "Cannot find city with min population"));

        double roadLength = Math.sqrt(Math.pow(cityWithMinPopulation.getCoordinates().getX(), 2) +
                Math.pow(cityWithMinPopulation.getCoordinates().getY(), 2));

        return ResponseEntity.status(200).body(new CityNameAndRoadLength(roadLength, cityWithMinPopulation.getName()));
    }
}
