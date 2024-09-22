package com.example.cardatabase.domain;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@RepositoryRestResource
public interface CarRepository extends CrudRepository<Car, Long> {
	// Fetch cars by brand
	List<Car> findByBrand(@Param("brand") String brand);
	// Fetch cars by color
	List<Car> findByColor(@Param("color") String color);
	// fetch cars by model year
	List<Car> findByModelyear(int modelYear);
    // Fetch cars by brand and model
    List<Car> findByBrandAndModel(String brand, String model);
    // Fetch cars by brand or color
    List<Car> findByBrandOrColor(String brand, String color);
    // Fetch cars by brand and sort by year
    List<Car> findByBrandOrderByModelyearAsc(String brand);
}