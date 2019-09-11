package br.com.rnascimento.api.resources;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.rnascimento.api.dtos.UserDTO;
import br.com.rnascimento.api.dtos.UserLoginDTO;
import br.com.rnascimento.api.services.UserService;
import br.com.rnascimento.api.utils.HashUtil;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserResourceTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Mock
	private UserService userService;
	
	@Ignore
	@Test
	public void findAllUsers() {
		List<UserDTO> userDTOList = Arrays.asList(UserDTO.builder().id(1L).build(), UserDTO.builder().id(2L).build());
		BDDMockito.when(this.userService.findAll()).thenReturn(userDTOList);
		
		ResponseEntity<String> response = this.restTemplate.getForEntity("/user/", String.class);
		
		Assert.assertEquals(response.getStatusCodeValue(), 200);
	}
	
	@Ignore
	@Test
	public void findAllUsersPaginator() {
		List<UserDTO> userDTOList = Arrays.asList(UserDTO.builder().id(1L).build(), UserDTO.builder().id(2L).build());
		BDDMockito.when(this.userService.findAll()).thenReturn(userDTOList);
		
		ResponseEntity<String> response = this.restTemplate.getForEntity("/user/list?page=" + 0 +"&size=" + 5, String.class);
		
		Assert.assertEquals(response.getStatusCodeValue(), 200);
	}
	
	@Ignore
	@Test
	public void findUserById() {
		Long id = 1L;
		UserDTO userDTO = UserDTO.builder().id(id).build();
		BDDMockito.when(this.userService.findById(id)).thenReturn(userDTO);
		
		ResponseEntity<String> response = this.restTemplate.getForEntity("/user/" + id, String.class);
		
		Assert.assertEquals(response.getStatusCodeValue(), 200);
	}
	
	@Ignore
	@Test
	public void deleteUserById() {
		Long id = 1L;
		
		this.restTemplate.delete("/user/" + id);
	}

	@Ignore
	@Test
	public void findUserByLoginAndPassword() {
		String login = "Administrator";
		String password = "administrator";
		String passwordHash = HashUtil.getSecureHash(password);
		
		UserLoginDTO userDTO = UserLoginDTO.builder().login(login).password(password).build();
		UserDTO userDTOReturn = UserDTO.builder().login(login).password(passwordHash).build();
		BDDMockito.when(this.userService.login(login, password)).thenReturn(userDTOReturn);
		
		ResponseEntity<UserDTO> response = this.restTemplate.postForEntity("/user/login", userDTO, UserDTO.class);
		
		Assert.assertEquals(response.getStatusCodeValue(), 200);
	}
	
}