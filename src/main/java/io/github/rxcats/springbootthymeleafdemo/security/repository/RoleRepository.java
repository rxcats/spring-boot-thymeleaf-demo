package io.github.rxcats.springbootthymeleafdemo.security.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import io.github.rxcats.springbootthymeleafdemo.security.entity.Role;

public interface RoleRepository extends MongoRepository<Role, ObjectId> {
    Role findByRole(String role);
}
