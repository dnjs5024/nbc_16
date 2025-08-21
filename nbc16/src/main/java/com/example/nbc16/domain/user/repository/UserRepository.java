package com.example.nbc16.domain.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nbc16.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
	public Optional<User> findByUserName(String userName);
}
