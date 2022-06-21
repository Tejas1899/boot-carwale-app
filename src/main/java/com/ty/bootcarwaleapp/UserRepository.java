package com.ty.bootcarwaleapp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByEmail(String email);

	List<User> findByRole(String role);

	List<User> findByPhone(String phone);

	User findByName(String name);

	@Query("Select u from User u where u.gender=?1 and u.role=?2")
	List<User> getData(String gender, String role);

	@Query("Select u from User u where u.email=:email and u.password=:password")
	List<User> validateUser(@Param("email") String email, @Param("password") String password);

}
