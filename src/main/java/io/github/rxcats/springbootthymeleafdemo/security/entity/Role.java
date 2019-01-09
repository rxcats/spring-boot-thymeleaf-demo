package io.github.rxcats.springbootthymeleafdemo.security.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Document
@Data
public class Role implements GrantedAuthority {

    @Id
    private ObjectId roleId;

    private String role;

    @Override
    public String getAuthority() {
        return role;
    }
}
