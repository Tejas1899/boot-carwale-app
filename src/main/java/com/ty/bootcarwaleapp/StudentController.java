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
public class StudentController {

	@Autowired
	StudentRepository repository;

	@PostMapping("/savestudent")
	public Student saveStudent(@RequestBody Student student) {
		return repository.save(student);
	}

	@GetMapping("/getallstud")
	public List<Student> getAllStudent() {
		return repository.findAll();
	}

	@GetMapping("/getstudbyid/{id}")
	public Student getById(@PathVariable int id) {
		Optional<Student> optional = repository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	@GetMapping("/deletestudent")
	public String deleteStudent(@RequestParam int id) {
		Optional<Student> optional = repository.findById(id);
		if (optional.isEmpty()) {
			return "Student not found";
		} else {
			repository.delete(optional.get());
			return "Student deleted";
		}
	}

	@PostMapping("/updatestudent/{id}")
	public Student updateStudent(@PathVariable int id, @RequestBody Student student) {
		Optional<Student> optional = repository.findById(id);
		if (optional.isEmpty()) {
			return null;
		} else {
			return repository.save(student);
		}
	}
}
