package com.example.cardatabase;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import com.example.cardatabase.domain.AppUser;
import com.example.cardatabase.domain.IAppUserRepository;
import com.example.cardatabase.domain.Car;
import com.example.cardatabase.domain.CarRepository;
import com.example.cardatabase.domain.Owner;
import com.example.cardatabase.domain.IOwnerRepository;

@SpringBootApplication
@EnableMethodSecurity
public class CardatabaseApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(
			CardatabaseApplication.class
	);

	private final CarRepository repository;
	private final IOwnerRepository ownerRepository;
	private final IAppUserRepository urepository;
	public CardatabaseApplication(CarRepository repository,
								  IOwnerRepository ownerRepository,
								  IAppUserRepository urepository) {
		this.repository = repository;
		this.ownerRepository = ownerRepository;
		this.urepository = urepository;
	}
	
	public static void main(String[] args) {
		// After adding this comment the application is restarte
		SpringApplication.run(CardatabaseApplication.class, args);
		logger.info("Application Started");
	}
	
    @Override
    public void run(String... args) throws Exception {
		// Add owner objects and save these to db
	    Owner owner1 = new Owner("John" , "Johnson");
	    Owner owner2 = new Owner("Mary" , "Robinson");
	    ownerRepository.saveAll(Arrays.asList(owner1,owner2));
	    
	    repository.save(new Car("Ford", "Mustang", "Red", "ADF-1121", 2023, 59000, owner1));
	    repository.save(new Car("Nissan", "Leaf", "White", "SSJ-3002", 2020, 29000, owner2));
	    repository.save(new Car("Toyota", "Prius", "Silver", "KKO-0212", 2022, 39000, owner2));
	    
	   // Los usuarios ya estan creados en la BBDD
	   // Username: user, password: user
       urepository.save(new AppUser("user",
           "$2a$10$NVM0n8ElaRgg7zWO1CxUdei7vWoPg91Lz2aYavh9.f9q0e4bRadue","USER"));
       // Username: admin, password: admin
       urepository.save(new AppUser("admin",
           "$2a$10$8cjz47bjbR4Mn8GMg9IZx.vyjhLXR/SKKMSZ9.mP9vpMu0ssKi8GW", "ADMIN"));

	   // Fetch all cars and log to console
	   for (Car car: repository.findAll()) {
			logger.info("brand: {}, model: {}", car.getBrand(), car.getModel());
		}

    }
}
