package com.sagitta.userservice.greeting;

import com.sagitta.userservice.greeting.domain.Greeting;
import com.sagitta.userservice.rest.client.ReqresClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GreetingServiceImpl implements GreetingService {
	
	private final GreetingRepository greetingRepository;
	private final Counter counter;
	private final ReqresClient reqresClient;
	
	@Override
	public String hello() {
		return "hello: " + counter.count();
	}
	
	@Override
	public void saveForLanguage(String language, String message) {
		Optional<Greeting> forLanguage = getForLanguage(language);
		if (forLanguage.isPresent()) {
			var greeting = forLanguage.get();
			greeting.setMessage(message);
			greetingRepository.save(greeting);
			counter.increment(message.length());
			return;
		}
		
		Greeting greetingEntity = Greeting.builder()
		                                  .language(language)
		                                  .message(message)
		                                  .build();
		greetingRepository.save(greetingEntity);
	}
	
	@Override
	public Optional<Greeting> getForLanguage(String language) {
		return greetingRepository.findByLanguage(language);
	}
	
	@Override
	public String getUser() {
		return reqresClient.getUser()
		                   .getData()
		                   .email();
	}
	
}
