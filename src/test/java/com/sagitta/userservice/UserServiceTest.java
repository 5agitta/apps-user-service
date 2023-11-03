package com.sagitta.userservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sagitta.userservice.user.UserService;
import com.sagitta.userservice.user.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testGetUserAge() throws ParseException {
        // Define a birth date for testing
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = dateFormat.parse("1990-01-15");

        // Call the getUserAge method
        int age = userService.getUserAge(birthDate);

        // Calculate the expected age based on the current date
        LocalDate currentDate = LocalDate.now();
        LocalDate expectedBirthDate = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int expectedAge = Period.between(expectedBirthDate, currentDate).getYears();

        // Verify that the actual age matches the expected age
        assertEquals(expectedAge, age);
    }
}

