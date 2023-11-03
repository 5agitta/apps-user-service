package com.sagitta.userservice.user;

import com.sagitta.userservice.user.domain.User;
import com.sagitta.userservice.user.domain.constants.CityCategory;
import com.sagitta.userservice.user.domain.constants.Gender;
import com.sagitta.userservice.user.domain.dto.UserInfoResponseDto;
import com.sagitta.userservice.user.domain.dto.UserUpdateRequestDto;
import com.sagitta.userservice.user.domain.dto.UserUpdateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<UserInfoResponseDto> getUserInfo(String etin) {
        Optional<User> optionalUser = userRepository.findByEtin(etin);
        if (!optionalUser.isPresent()) {
            User user = User.builder()
                    .etin(etin)
                    .build();
            userRepository.save(user);
            return ResponseEntity.ok(getUserEntityToDto(user));
        }
        User user = optionalUser.get();
        return ResponseEntity.ok(getUserEntityToDto(user));
    }

    public ResponseEntity<UserInfoResponseDto> updateUser(UserUpdateRequestDto userUpdateRequestDto) {

        Optional<User> optionalUser = userRepository.findByEtin(userUpdateRequestDto.getEtin());
        if (!optionalUser.isPresent()) {
            User user = getDtoToEntity(userUpdateRequestDto);
            userRepository.save(user);
            return ResponseEntity.ok(getUserEntityToDto(user));

        }
        User user = optionalUser.get();
        if(userUpdateRequestDto.getName() != null)
            user.setName(userUpdateRequestDto.getName());
        if(userUpdateRequestDto.getEmail() != null)
            user.setEmail(userUpdateRequestDto.getEmail());
        if(userUpdateRequestDto.getPhone() != null)
            user.setPhone(userUpdateRequestDto.getPhone());
        if(userUpdateRequestDto.getAddress() != null)
            user.setAddress(getUserCityCategory(userUpdateRequestDto.getAddress()));
        if(convertDOB(userUpdateRequestDto.getDob()) != null)
            user.setDob(convertDOB(userUpdateRequestDto.getDob()));
        if(userUpdateRequestDto.getGender() != null)
            user.setGender(getUserGender(userUpdateRequestDto.getGender()));


        userRepository.save(user);

        // Create and return a response DTO with the updated user details
        UserInfoResponseDto responseDto = getUserEntityToDto(user);


        return ResponseEntity.ok(responseDto);

    }

    public int getUserAge(Date dob) {
        // Convert the Date to a LocalDate
        LocalDate birthDate = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Calculate the age
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);

        return period.getYears();
    }

    private Gender getUserGender(String gender) {
        if(gender.equals(Gender.MALE.getGender()))
            return Gender.MALE;
        else return Gender.FEMALE;
    }

    public CityCategory getUserCityCategory(String address) {
        if (address.equals(CityCategory.DHAKA_OR_CHITTAGONG.getName()))
            return CityCategory.DHAKA_OR_CHITTAGONG;
        else if (address.equals(CityCategory.OTHER_CITY.getName()))
            return CityCategory.OTHER_CITY;
        else
            return CityCategory.NON_CITY;
    }

    public Date convertDOB(String dob) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            return formatter.parse(dob);
        } catch (ParseException e) {
            return null;
        }
    }

    private UserInfoResponseDto getUserEntityToDto(User user) {
        UserInfoResponseDto userInfoResponseDto = new UserInfoResponseDto();
        if (user.getEtin() != null) {
            userInfoResponseDto.setEtin(user.getEtin());
        }
        if (user.getName() != null) {
            userInfoResponseDto.setName(user.getName());
        }
        if (user.getEmail() != null) {
            userInfoResponseDto.setEmail(user.getEmail());
        }
        if (user.getPhone() != null) {
            userInfoResponseDto.setPhone(user.getPhone());
        }
        if (user.getAddress() != null) {
            userInfoResponseDto.setAddress(user.getAddress().toString());
        }
        if (user.getDob() != null) {
            userInfoResponseDto.setAge(getUserAge(user.getDob()));
        }
        if (user.getGender() != null) {
            userInfoResponseDto.setGender(user.getGender().toString());
        }
        return userInfoResponseDto;
    }

    private User getDtoToEntity(UserUpdateRequestDto userUpdateRequestDto) {
        User user = new User();
        if (userUpdateRequestDto.getEtin() != null) {
            user.setEtin(userUpdateRequestDto.getEtin());
        }
        if (userUpdateRequestDto.getName() != null) {
            user.setName(userUpdateRequestDto.getName());
        }
        if (userUpdateRequestDto.getEmail() != null) {
            user.setEmail(userUpdateRequestDto.getEmail());
        }
        if (userUpdateRequestDto.getPhone() != null) {
            user.setPhone(userUpdateRequestDto.getPhone());
        }
        if (userUpdateRequestDto.getAddress() != null) {
            user.setAddress(getUserCityCategory(userUpdateRequestDto.getAddress()));
        }
        if (userUpdateRequestDto.getDob() != null) {
            user.setDob(convertDOB(userUpdateRequestDto.getDob()));
        }
        return user;
    }
}
