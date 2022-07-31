package com.example.asmspringboot.entity.entityEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum UserRole {
    ADMIN(1),
    USER(0),
    UNDEFINED(2);
    private int value;

    public static UserRole of(int value) {
        for (UserRole userRole : UserRole.values()) {
            if(userRole.getValue() == value) {
                return userRole;
            }
        }
        return UserRole.UNDEFINED;
    }
}
