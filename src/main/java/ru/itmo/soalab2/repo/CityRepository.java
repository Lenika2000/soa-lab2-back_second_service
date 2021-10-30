package ru.itmo.soalab2.repo;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.soalab2.model.City;

@Repository
public interface  CityRepository extends CrudRepository<City, Long> {
    City findTopByOrderByAreaDesc();
    City findTopByOrderByPopulationAsc();
    City findByName(String name);
}
