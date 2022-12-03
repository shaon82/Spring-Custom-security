package com.shaon.springsecurity;

import com.shaon.springsecurity.model.UserEntity;
import com.shaon.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringSecurityApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(1L);
		userEntity.setName("shaon");
		userEntity.setUsername("1111");
		userEntity.setPassword(new BCryptPasswordEncoder().encode("1234"));
		userRepository.save(userEntity);
	}
}
