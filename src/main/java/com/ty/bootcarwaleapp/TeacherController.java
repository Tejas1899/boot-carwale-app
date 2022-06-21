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
public class TeacherController {

	@Autowired
	TeacherRepository repository;

	@PostMapping("/saveteacher")
	public Teacher saveTeacher(@RequestBody Teacher teacher) {
		return repository.save(teacher);
	}

	@GetMapping("/getallteacher")
	public List<Teacher> getAllTeacher() {
		return repository.findAll();
	}

	@GetMapping("/getteacherbyid/{id}")
	public Teacher getTeacherById(@PathVariable int id) {
		Optional<Teacher> optional = repository.findById(id);
		if (optional.isEmpty()) {
			return null;
		} else {
			return optional.get();
		}
	}

	@GetMapping("/deleteteacher")
	public String deleteTeacher(@RequestParam int id) {
		Optional<Teacher> optional = repository.findById(id);
		if (optional.isEmpty()) {
			return "No teacher found";
		} else {
			repository.delete(optional.get());
			return "Teacher deleted";
		}
	}

	@PostMapping("/updateteacher/{id}")
	public Teacher updateTeacher(@PathVariable int id, @RequestBody Teacher teacher) {
		Optional<Teacher> optional = repository.findById(id);
		if (optional.isEmpty()) {
			return null;
		} else {
		 return	repository.save(teacher);
		}
	}
}
