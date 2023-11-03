package com.sagitta.userservice.user;


import com.sagitta.userservice.user.domain.dto.UserInfoRequestDto;
import com.sagitta.userservice.user.domain.dto.UserInfoResponseDto;
import com.sagitta.userservice.user.domain.dto.UserUpdateRequestDto;
import com.sagitta.userservice.user.domain.dto.UserUpdateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/update")
    public ResponseEntity<UserInfoResponseDto> updateUser(@RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        return userService.updateUser(userUpdateRequestDto);
    }

    @PostMapping("/info")
    @ResponseBody
    public ResponseEntity<UserInfoResponseDto> getUserInfo(@RequestBody UserInfoRequestDto userInfoRequestDto) {
        return userService.getUserInfo(userInfoRequestDto.getEtin());
    }
}
