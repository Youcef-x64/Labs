package com.example.demo;

import com.example.demo.model.Address;
import com.example.demo.service.AddressService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private AddressService addressService;

	public DemoApplication(AddressService addressService) {
		this.addressService = addressService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var address = new Address(null, "1000 N 4th ST", "Fairfield", "IA");

		// Create
		addressService.save(address);

		// Read
		var _address = addressService.findById(1);

		// Update
		_address.setStreet("500 N 4th ST");

		addressService.save(_address);

		// Delete
		addressService.delete(_address);

	}
}
