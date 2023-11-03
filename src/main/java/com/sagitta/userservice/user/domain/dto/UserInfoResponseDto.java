package com.sagitta.userservice.user.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoResponseDto {
    private String etin;
    private String name;
    private String email;
    private String phone;
    private String address;
    private int age;
    private String gender;
}
