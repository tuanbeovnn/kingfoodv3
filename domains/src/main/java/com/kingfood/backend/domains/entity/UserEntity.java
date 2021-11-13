package com.kingfood.backend.domains.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kingfood.backend.domains.enums.AuthProvider;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity extends BaseEntity {

    @Column(name = "email")
    private String email;

    @Column(name = "user_name")
    private String userName;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private int status;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "is_active")
    private Integer isActive;

    private int practiceTime;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "userid"),
            inverseJoinColumns = @JoinColumn(name = "roleid"))
    private List<RoleEntity> roles = new ArrayList<>();
}
