package com.sagitta.userservice.greeting;

import com.sagitta.userservice.greeting.domain.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GreetingRepository extends JpaRepository<Greeting, Long> {
	
	@Query ("select g from Greeting g where g.language = :language")
	Optional<Greeting> findByLanguage(String language);
}
