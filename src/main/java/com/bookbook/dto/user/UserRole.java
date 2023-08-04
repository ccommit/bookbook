package com.bookbook.dto.user;

import lombok.Getter;

@Getter
public enum UserRole {
    USER("user"),
    ADMIN("admin");

    private final String name;

    UserRole(String name) {
        this.name = name;
    }

    public static UserRole getByUpperCaseName(String name) {
        try {
            return UserRole.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception);
        }
    }
}
