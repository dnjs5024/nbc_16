package com.example.nbc16.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickName;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public User(String userName, String password, String nickName, UserRole userRole) {
        this.userName = userName;
        this.password = password;
        this.nickName = nickName;
        this.userRole = userRole;
    }
    public void updateRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
