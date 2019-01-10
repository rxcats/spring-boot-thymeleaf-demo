package io.github.rxcats.springbootthymeleafdemo.security.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class User {

    @Id
    private ObjectId userId;

    @Email(message = "invalid email address.")
    @NotEmpty(message = "required email.")
    private String email;

    @Length(min = 4)
    private String password;

    @Transient
    private String passwordVerify;

    private String name;

    // 0: default, 1: active, 2: none active
    private int active;

    private List<ObjectId> roleIds;

    @Transient
    private List<Role> roles;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

    public void setRoles(List<Role> roles) {
        this.roles = roles;

        if (roleIds == null) {
            this.roleIds = roles.stream()
                .map(Role::getRoleId)
                .collect(Collectors.toList());
        }
    }

    @AssertTrue(message = "password does not match the confirm password.")
    private boolean isValidPassowrd() {
        return password.equals(passwordVerify);
    }

}
