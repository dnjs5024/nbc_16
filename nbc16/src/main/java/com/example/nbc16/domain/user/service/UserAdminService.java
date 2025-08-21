package com.example.nbc16.domain.user.service;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nbc16.common.dto.ResponseCode;
import com.example.nbc16.common.exception.BusinessException;
import com.example.nbc16.domain.user.dto.response.UserRoleChangeResponseDto;
import com.example.nbc16.domain.user.entity.User;
import com.example.nbc16.domain.user.entity.UserRole;
import com.example.nbc16.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserAdminService {

	private final UserRepository userRepository;

	@Transactional
	public UserRoleChangeResponseDto changeUserRole(long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new BusinessException(ResponseCode.USER_NOT_FOUND));
		user.updateRole(UserRole.ADMIN);
		return UserRoleChangeResponseDto.from(
			user.getUserName(),
			user.getNickName(),
			new ArrayList<>(List.of(user.getUserRole()))
			);
	}
}
