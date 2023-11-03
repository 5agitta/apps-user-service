package com.sagitta.userservice.greeting;

import com.sagitta.userservice.greeting.domain.Greeting;

import java.util.Optional;

public interface GreetingService {
	
	String hello();
	
	void saveForLanguage(String language, String message);
	
	Optional<Greeting> getForLanguage(String language);
	
	String getUser();
}
