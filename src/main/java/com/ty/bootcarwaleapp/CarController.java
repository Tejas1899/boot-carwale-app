package com.ty.bootcarwaleapp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

	@Autowired
	CarRepository carRepository;

	@PostMapping("/savecar")
	public Car saveCar(@RequestBody Car car) {
		return carRepository.save(car);
	}

	@GetMapping("/getall")
	public List<Car> getAllCars() {
		return carRepository.findAll();
	}

	@GetMapping("/getbyid/{id}")
	public Car getCarById(@PathVariable int id) {
		Optional<Car> optional = carRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		} else {
			return optional.get();
		}
	}

	@GetMapping("/deletecar")
	public String deleteCar(@RequestParam int id) {
		Optional<Car> car = carRepository.findById(id);
		if (car.isEmpty()) {
			return "No car present";
		} else {
			carRepository.delete(car.get());
			return "Car is deleted";

		}
	}

	@GetMapping("/updatesave/{id}")
	public Car updateCar(@PathVariable int id,@RequestBody Car car) {
		Optional<Car> opt = carRepository.findById(id);
		if(opt.isEmpty()) {
			return null;
		}else {
			return carRepository.save(car);
		}
	}
}
