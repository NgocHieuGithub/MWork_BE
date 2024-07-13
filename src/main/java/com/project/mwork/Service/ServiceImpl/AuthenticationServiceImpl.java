package com.project.mwork.Service.ServiceImpl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.project.mwork.DTO.Request.CUserRequest;
import com.project.mwork.DTO.Request.SignInRequest;
import com.project.mwork.DTO.Response.TokenResponse;
import com.project.mwork.Mapper.UserMapper;
import com.project.mwork.Model.User;
import com.project.mwork.Repository.UserRepository;
import com.project.mwork.Service.AuthenticationService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService{
	
	String SIGNER_KEY = "06d5c53a9f500e84b87123ed665c48857c3c62cfe2795087f71c364c7afd6dd54bcec205ba6d593ab8b8ed04a9b35c4d3e8592aacc5c193651cd19734a4bd2b2";
	
	PasswordEncoder passwordEncoder;
	UserRepository repository;
	UserMapper userMapper;

	@Override
	public TokenResponse SignIn(SignInRequest request) {
		User user = repository
				.findByUsername(request.getUsername())
				.orElse(null);
		if (!user.getPassword().matches(request.getPassword()) && !user.getPassword().matches(passwordEncoder.encode(request.getPassword()))) {
			throw new RuntimeException("Wrong password");
		}
		TokenResponse token = TokenResponse
				.builder()
				.user(userMapper.toUserResponse(user))
				.accessToken(GenerateAcToken(user))
				.refreshToken(GenerateRfToken(user))
				.build();
		return token;
	}

	@Override
	public TokenResponse SignUp(CUserRequest request) {
		if(repository.existsByUsername(request.getUsername())) {
			throw new RuntimeException("Username exits");
		}
		User user = userMapper.toUser(request);
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user = repository.save(user);
		TokenResponse token = TokenResponse
				.builder()
				.user(userMapper.toUserResponse(user))
				.accessToken(GenerateAcToken(user))
				.refreshToken(GenerateRfToken(user))
				.build();
		return token;
	}

	@Override
	public void LogOut() {
		// TODO Auto-generated method stub
		
	}
	
	
	private String GenerateAcToken(User user) {
		JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
		JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
				.expirationTime(new Date(
                        Instant.now().plus(3600, ChronoUnit.SECONDS).toEpochMilli()
                ))
                .jwtID(UUID.randomUUID().toString())
                .subject(user.getId())
                .claim("user_Id", user.getId())
                .build();
		Payload payload = new Payload(jwtClaimsSet.toJSONObject());
		JWSObject jwsObject = new JWSObject(header, payload);
		try {
			jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
			return jwsObject.serialize();
		} catch (JOSEException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	private String GenerateRfToken(User user) {
		JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
		JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
				.expirationTime(new Date(
                        Instant.now().plus(36000, ChronoUnit.SECONDS).toEpochMilli()
                ))
                .jwtID(UUID.randomUUID().toString())
                .subject(user.getId())
                .build();
		Payload payload = new Payload(jwtClaimsSet.toJSONObject());
		JWSObject jwsObject = new JWSObject(header, payload);
		try {
			jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
			return jwsObject.serialize();
		} catch (JOSEException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
}
