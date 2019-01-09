package io.github.rxcats.springbootthymeleafdemo.security.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import io.github.rxcats.springbootthymeleafdemo.security.entity.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByEmail(String email);
}
