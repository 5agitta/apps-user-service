package com.sagitta.userservice.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponseDto {
    private String etin;
    private String name;
    private String email;
    private String phone;
    private String address;
    private int age;
    private String gender;
}
