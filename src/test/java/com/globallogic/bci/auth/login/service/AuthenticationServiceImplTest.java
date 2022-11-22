package com.globallogic.bci.auth.login.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.globallogic.bci.auth.login.dto.SignUpRequest;
import com.globallogic.bci.auth.login.dto.SignUpResponse;
import com.globallogic.bci.auth.login.exception.BCIException;
import com.globallogic.bci.auth.login.model.User;
import com.globallogic.bci.auth.login.repository.UsersRepository;

@SpringBootTest
public class AuthenticationServiceImplTest {

	@Autowired
	private AuthenticationService service;

	@MockBean
	private UsersRepository repository;

	private SignUpRequest request;

	@BeforeEach
	public void setUp() {
		User user = new User();
		user.setId(UUID.randomUUID());

		request = new SignUpRequest();
		when(repository.save(any())).thenReturn(user);
	}

	@Test
	public void signUpSuccessful() {
		request.setEmail("test@test.com");
		request.setPassword("0Password1");
		SignUpResponse response = service.signUp(request);
		
		assertNotNull(response.getToken());
		assertNotNull(response.getId());
		assertNotNull(response.getCreated());
		assertTrue(response.isActive());
		assertNull(response.getLastLogin());
	}

	@Test
	public void signUpShouldThrow101() {
		request.setEmail("test@test.com");
		request.setPassword("0Password1");
		when(repository.findByEmail(anyString())).thenReturn(Optional.of(new User()));
		BCIException ex = assertThrows(BCIException.class, () -> service.signUp(request),
				"Expected sign up, but throws User already exist error.");
		assertEquals(ex.getErrorCode(), "BCI_ERR_101");
	}

	@Test
	public void signUpShouldThrow102() {
		BCIException ex = assertThrows(BCIException.class, () -> service.signUp(request),
				"Expected sign up, but throws wrong email format.");
		assertEquals(ex.getErrorCode(), "BCI_ERR_102");
	}

	@Test
	public void signUpShouldThrow103() {
		request.setEmail("test@test.com");
		BCIException ex = assertThrows(BCIException.class, () -> service.signUp(request),
				"Expected sign up, but throws wrong email format.");
		assertEquals(ex.getErrorCode(), "BCI_ERR_103");
	}
}
