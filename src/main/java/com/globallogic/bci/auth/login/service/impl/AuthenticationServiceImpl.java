package com.globallogic.bci.auth.login.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.globallogic.bci.auth.login.dto.SignUpRequest;
import com.globallogic.bci.auth.login.dto.SignUpResponse;
import com.globallogic.bci.auth.login.exception.BCIException;
import com.globallogic.bci.auth.login.model.Phone;
import com.globallogic.bci.auth.login.model.User;
import com.globallogic.bci.auth.login.repository.UsersRepository;
import com.globallogic.bci.auth.login.service.AuthenticationService;
import com.globallogic.bci.auth.login.utils.ValidationUtils;

import io.jsonwebtoken.Jwts;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private static final int TTL_TOKEN = 300000;
	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public SignUpResponse signUp(SignUpRequest request) {
		this.validateInput(request);
		Optional<User> optionalUser = usersRepository.findByEmail(request.getEmail());
		if (optionalUser.isPresent()) {
			throw new BCIException("BCI_ERR_101", "El email utilizado ya esta en uso.");
		}

		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());

		Set<Phone> phones = new HashSet<Phone>();

		user.setPassword(passwordEncoder.encode(request.getPassword()));

		if (Objects.nonNull(request.getPhones())) {
			request.getPhones().forEach(p -> {
				Phone newPhone = new Phone();
				newPhone.setNumber(p.getNumber());
				newPhone.setCitycode(p.getCitycode());
				newPhone.setCountrycode(p.getCountrycode());
				phones.add(newPhone);

			});
			user.setPhones(phones);
		}

		user = usersRepository.save(user);

		SignUpResponse result = new SignUpResponse();
		result.setId(user.getId());
		result.setActive(true);
		result.setCreated(new Date());
		result.setLastLogin(null);
		result.setToken(this.generateJWT(user));

		return result;
	}

	private void validateInput(SignUpRequest request) {
		if (Objects.isNull(request.getEmail()) || !ValidationUtils.emailMatches(request.getEmail())) {
			throw new BCIException("BCI_ERR_102", "Formato de mail invalido");
		}
		if (Objects.isNull(request.getPassword()) || !ValidationUtils.passwordMatches(request.getPassword())) {
			throw new BCIException("BCI_ERR_103", "Formato de contraseña invalido");
		}

	}

	private String generateJWT(User user) {
		return Jwts.builder().setId("id").setIssuedAt(new Date()).setIssuer("BCI").setSubject(user.getEmail())
				.setExpiration(new Date(System.currentTimeMillis() + TTL_TOKEN)).compact();
	}

}
