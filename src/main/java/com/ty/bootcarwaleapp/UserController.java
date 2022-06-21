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
public class UserController {

	@Autowired
	UserRepository repository;

	@PostMapping("/saveuser")
	public User saveUser(@RequestBody User user) {
		return repository.save(user);
	}

	@GetMapping("/getalluser")
	public List<User> getAllUser() {
		return repository.findAll();
	}

	@GetMapping("/getuserbyid/{id}")
	public User getUserById(@PathVariable int id) {
		Optional<User> optional = repository.findById(id);
		if (optional.isEmpty()) {
			return null;
		} else {
			return optional.get();
		}
	}

	@GetMapping("/deleteuser")
	public String deleteUser(@RequestParam int id) {
		Optional<User> optional = repository.findById(id);
		if (optional.isEmpty()) {
			return "No User found";
		} else {
			repository.delete(optional.get());
			return "User deleted";
		}
	}

	@PostMapping("/updateuser/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User user) {
		Optional<User> optional = repository.findById(id);
		if (optional.isEmpty()) {
			return null;
		} else {
			return repository.save(user);
		}
	}

	@GetMapping("/getbyemail")
	public List<User> getByEmail(@RequestParam String email) {
		return repository.findByEmail(email);
	}

	@GetMapping("/getbyrole")
	public List<User> getByRole(@RequestParam String role) {
		return repository.findByRole(role);
	}
	
	@GetMapping("/getbyphone/{phone}")
	public List<User> getByPhone(@PathVariable String phone){
		return repository.findByPhone(phone);
	}
	
	@GetMapping("/getbyname/{name}")
	public User getByName(@PathVariable String name) {
		return repository.findByName(name);
	}
	
	@GetMapping("/getdata/{gender}/{role}")
	public List<User> getSomeData(@PathVariable String gender,@PathVariable String role){
		return repository.getData(gender, role);
	}
	
	@GetMapping("/validateuser")
	public List<User> validateUserData(@RequestParam String email,@RequestParam String password){
		return repository.validateUser(email, password);
	}
}
