package com.pauloedney.metachallenge.repositories;

import com.pauloedney.metachallenge.models.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String> {
  UserDetails findByUsername(String username);
}
