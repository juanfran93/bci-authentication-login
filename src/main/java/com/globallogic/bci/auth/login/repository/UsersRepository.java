package com.globallogic.bci.auth.login.repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globallogic.bci.auth.login.model.User;

@Repository
public interface UsersRepository extends JpaRepository<User, BigInteger> {

	public Optional<User> findByEmail(String email);

}
