package com.sagitta.userservice.user.domain.constants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    ;
    private final String gender;
    public String getGender() {
        return this.gender;
    }
}
