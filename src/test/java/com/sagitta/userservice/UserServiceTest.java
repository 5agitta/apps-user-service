package com.sagitta.userservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sagitta.userservice.user.UserService;
import com.sagitta.userservice.user.UserServiceImpl;
import com.sagitta.userservice.user.domain.constants.CityCategory;
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

    @Test
    public void testConvertDOB() throws ParseException {
        // Define a sample date of birth string
        String dobString = "15/01/1990";

        // Call the convertDOB method
        Date dob = userService.convertDOB(dobString);

        // Define the expected Date object based on the sample string
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date expectedDob = dateFormat.parse(dobString);

        // Verify that the actual Date matches the expected Date
        assertEquals(expectedDob, dob);
    }

    @Test
    public void testConvertDOBWithInvalidFormat() {
        // Define an invalid date of birth string
        String invalidDobString = "01-15-1990";

        // Call the convertDOB method with an invalid format
        Date dob = userService.convertDOB(invalidDobString);

        // Verify that the result is null for an invalid format
        assertEquals(null, dob);
    }

    @Test
    public void testGetUserCityCategory() {
        // Test with valid city category names
        assertEquals(CityCategory.DHAKA_OR_CHITTAGONG, userService.getUserCityCategory(CityCategory.DHAKA_OR_CHITTAGONG.getName()));
        assertEquals(CityCategory.OTHER_CITY, userService.getUserCityCategory(CityCategory.OTHER_CITY.getName()));

        // Test with an invalid city category name
        assertEquals(CityCategory.NON_CITY, userService.getUserCityCategory("InvalidCity"));
    }
}

