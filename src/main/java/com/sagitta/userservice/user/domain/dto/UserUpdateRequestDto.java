package com.sagitta.userservice.user.domain.dto;

import lombok.Data;

@Data
public class UserUpdateRequestDto {
    private String etin;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String dob;
    private String gender;
}
