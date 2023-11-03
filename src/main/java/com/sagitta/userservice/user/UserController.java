package com.sagitta.userservice.user;


import com.sagitta.userservice.user.domain.dto.UserInfoRequestDto;
import com.sagitta.userservice.user.domain.dto.UserInfoResponseDto;
import com.sagitta.userservice.user.domain.dto.UserUpdateRequestDto;
import com.sagitta.userservice.user.domain.dto.UserUpdateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/update")

    public UserUpdateResponseDto updateUser(UserUpdateRequestDto userUpdateRequestDto) {
        return userService.updateUser(userUpdateRequestDto);
    }

    @PostMapping("/info")
    public UserInfoResponseDto getUserInfo(UserInfoRequestDto userInfoRequestDto) {
        return userService.getUserInfo(userInfoRequestDto.getEtin());
    }
}
