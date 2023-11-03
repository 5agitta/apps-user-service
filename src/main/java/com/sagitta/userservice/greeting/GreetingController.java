package com.sagitta.userservice.greeting;

import com.sagitta.userservice.greeting.domain.CreateGreetingDto;
import com.sagitta.userservice.greeting.domain.Greeting;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping ("/greeting")
public class GreetingController {
	
	private final GreetingService greetingService;
	
	@GetMapping ("/hello")
	public String getHello() {
		return greetingService.hello();
	}
	
	@PostMapping ("/save-for-language")
	public String saveGreetingForLanguage(@RequestBody CreateGreetingDto createGreetingDto) {
		greetingService.saveForLanguage(createGreetingDto.language(), createGreetingDto.message());
		return "Saved";
	}
	
	@GetMapping ("/get-for-language")
	public String getGreetingForLanguage(@RequestParam String language) {
		return greetingService.getForLanguage(language)
		                      .map(Greeting::getMessage)
		                      .orElse("No greeting found");
	}
	
	@GetMapping ("/user")
	public String getUser() {
		return greetingService.getUser();
	}
	
}
