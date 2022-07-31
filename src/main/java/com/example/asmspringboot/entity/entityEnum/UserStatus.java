package com.example.asmspringboot.entity.entityEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum UserStatus {
    ACTIVE(1),
    DEACTIVE(0),
    DELETED(-1),
    UNDEFINED(-2);
    private int value;

    public static UserStatus of(int value) {
        for (UserStatus userStatus : UserStatus.values()) {
            if(userStatus.getValue() == value) {
                return userStatus;
            }
        }
        return UserStatus.UNDEFINED;
    }
}
