package com.example.nbc16.domain.auth.service;

import static com.example.nbc16.common.dto.ResponseCode.INVALID_CREDENTIALS;
import static com.example.nbc16.common.dto.ResponseCode.USER_ALREADY_EXISTS;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.nbc16.common.exception.BusinessException;
import com.example.nbc16.common.util.JwtUtil;
import com.example.nbc16.domain.auth.dto.response.AuthResponseDto;
import com.example.nbc16.domain.auth.dto.request.LoginRequestDto;
import com.example.nbc16.domain.auth.dto.request.SignInRequestDto;
import com.example.nbc16.domain.user.entity.User;
import com.example.nbc16.domain.user.entity.UserRole;
import com.example.nbc16.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final JwtUtil jwtUtil;

	public AuthResponseDto signIn(SignInRequestDto signInRequest) {

		userRepository.findByUserName(signInRequest.getUserName())
			.ifPresent(user -> { throw new BusinessException(USER_ALREADY_EXISTS); });

		String userName = signInRequest.getUserName();
		String password = passwordEncoder.encode(signInRequest.getPassword());
		String nickName = signInRequest.getNickName();
		UserRole userRole = signInRequest.getUserRole();

		User saveUser = new User(
			userName,
			password,
			nickName,
			userRole
			);

		 userRepository.save(saveUser);

		return AuthResponseDto.from(userName, nickName, new ArrayList<>(List.of(userRole)));
	}

	public String logIn(LoginRequestDto loginRequest) {

		User user = userRepository.findByUserName(loginRequest.getUserName())
			.orElseThrow(() -> new BusinessException(INVALID_CREDENTIALS));

		if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
			throw new BusinessException(INVALID_CREDENTIALS);
		}

		return jwtUtil.createToken(user.getId(), user.getUserName(), user.getUserRole());
	}
}
