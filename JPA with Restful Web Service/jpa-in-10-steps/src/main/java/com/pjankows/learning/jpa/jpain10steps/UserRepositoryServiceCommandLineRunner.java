package com.pjankows.learning.jpa.jpain10steps;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.pjankows.learning.jpa.jpain10steps.entity.User;
import com.pjankows.learning.jpa.jpain10steps.service.UserRepository;

@Component
public class UserRepositoryServiceCommandLineRunner implements CommandLineRunner {
	
	private static final Logger log = LoggerFactory.getLogger(UserRepositoryServiceCommandLineRunner.class);

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		User user = new User("Jill", "Ädmin"); 
		userRepository.save(user);
		log.info("New user is created: " + user);
		
		Optional<User> userWithIdOne = userRepository.findById(1L);
		log.info("New user is retrieved: " + userWithIdOne);
		
		List<User> users = userRepository.findAll();
		log.info("All: " + users);
	}

}
