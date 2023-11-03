package com.sagitta.userservice.greeting.domain;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Setter
@Table (schema = "test",
		uniqueConstraints = @UniqueConstraint (columnNames = {"language"}))
public class Greeting {
	
	@Id
	@GeneratedValue (strategy = GenerationType.UUID)
	private String id;
	
	@Nonnull
	private String language;
	
	@Nonnull
	private String message;
	
}
