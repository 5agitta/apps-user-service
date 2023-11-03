package com.sagitta.userservice.user;

import com.sagitta.userservice.user.domain.dto.UserInfoRequestDto;
import com.sagitta.userservice.user.domain.dto.UserInfoResponseDto;
import com.sagitta.userservice.user.domain.dto.UserUpdateRequestDto;
import com.sagitta.userservice.user.domain.dto.UserUpdateResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<UserInfoResponseDto> updateUser(UserUpdateRequestDto userUpdateRequestDto);

    ResponseEntity<UserInfoResponseDto> getUserInfo(String etin);
}
