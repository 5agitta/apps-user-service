package com.sagitta.userservice.user.domain;

import com.sagitta.userservice.user.domain.constants.CityCategory;
import com.sagitta.userservice.user.domain.constants.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Setter
@Data
@Table(schema = "tax")
public class User {
    @Id
    private String etin;
    private String name;
    private String email;
    private String password;
    private String phone;
    private CityCategory address;
    private Date dob;
    private Gender gender;
}
