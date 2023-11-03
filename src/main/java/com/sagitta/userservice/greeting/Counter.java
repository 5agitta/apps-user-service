package com.sagitta.userservice.greeting;

import com.sagitta.userservice.logging.annotations.LoggingClass;
import com.sagitta.userservice.logging.annotations.NoLogging;
import org.springframework.stereotype.Component;

@Component
@LoggingClass
public class Counter {
	
	private int count;
	
	public int count() {
		return count;
	}
	
	@NoLogging
	public void increment(int i) {
		count += i;
	}
	
}
