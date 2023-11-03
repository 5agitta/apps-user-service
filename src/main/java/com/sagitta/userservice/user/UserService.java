package com.sagitta.userservice.user;

import com.sagitta.userservice.user.domain.dto.UserInfoRequestDto;
import com.sagitta.userservice.user.domain.dto.UserInfoResponseDto;
import com.sagitta.userservice.user.domain.dto.UserUpdateRequestDto;
import com.sagitta.userservice.user.domain.dto.UserUpdateResponseDto;

public interface UserService {
    UserUpdateResponseDto updateUser(UserUpdateRequestDto userUpdateRequestDto);

    UserInfoResponseDto getUserInfo(String etin);
}
