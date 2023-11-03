package com.sagitta.userservice.greeting.domain;

import java.io.Serializable;


public record CreateGreetingDto(String language, String message) implements Serializable {
	
}