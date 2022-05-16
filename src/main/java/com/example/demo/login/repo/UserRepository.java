package com.example.demo.login.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	User findByEmailId(String emailId);
}
