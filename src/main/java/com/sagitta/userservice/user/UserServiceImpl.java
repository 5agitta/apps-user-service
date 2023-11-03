package com.sagitta.userservice.user;

import com.sagitta.userservice.user.domain.User;
import com.sagitta.userservice.user.domain.constants.CityCategory;
import com.sagitta.userservice.user.domain.dto.UserInfoResponseDto;
import com.sagitta.userservice.user.domain.dto.UserUpdateRequestDto;
import com.sagitta.userservice.user.domain.dto.UserUpdateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserInfoResponseDto getUserInfo(String etin) {
        Optional<User> optionalUser = userRepository.findByEtin(etin);
        if (!optionalUser.isPresent()) {
            return null;
        }
        User user = optionalUser.get();
        return UserInfoResponseDto.builder()
                .etin(user.getEtin())
                .address(user.getAddress().toString())
                .age(getUserAge(user.getDob()))
                .phone(user.getPhone())
                .email(user.getEmail())
                .name(user.getName())
                .gender(user.getGender().toString())
                .build();
    }

    public UserUpdateResponseDto updateUser(UserUpdateRequestDto userUpdateRequestDto) {
    try {
        Optional<User> optionalUser = userRepository.findByEtin(userUpdateRequestDto.getEtin());
        if (!optionalUser.isPresent()) {
            return null;
        }
        User user = optionalUser.get();
        user.setAddress(getUserCityCategory(userUpdateRequestDto.getAddress()));
        user.setDob(convertDOB(userUpdateRequestDto.getDob()));
        user.setName(userUpdateRequestDto.getName());

        userRepository.save(user);

        // Create and return a response DTO with the updated user details
        UserUpdateResponseDto responseDto = UserUpdateResponseDto.builder()
                .etin(user.getEtin())
                .address(user.getAddress().toString())
                .age(getUserAge(user.getDob()))
                .phone(user.getPhone())
                .email(user.getEmail())
                .name(user.getName())
                .gender(user.getGender().toString())
                .build();


        return responseDto;
    } catch (ParseException ex) {
        // Handle the ParseException, e.g., log an error or return an error response
        return null; // Create an error response method
    }
}

    public int getUserAge(Date dob) {
        // Convert the Date to a LocalDate
        LocalDate birthDate = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Calculate the age
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);

        return period.getYears();
    }

    public CityCategory getUserCityCategory(String address) {
        if (address.equals(CityCategory.DHAKA_OR_CHITTAGONG.getName()))
            return CityCategory.DHAKA_OR_CHITTAGONG;
        else if (address.equals(CityCategory.OTHER_CITY.getName()))
            return CityCategory.OTHER_CITY;
        else
            return CityCategory.NON_CITY;
    }

    public Date convertDOB(String dob) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.parse(dob);
    }
}
