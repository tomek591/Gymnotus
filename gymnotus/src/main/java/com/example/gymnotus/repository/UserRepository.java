package com.example.gymnotus.repository;

import com.example.gymnotus.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
