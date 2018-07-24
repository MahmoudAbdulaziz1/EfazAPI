package com.taj.model;

import java.util.Arrays;

/**
 * Created by User on 7/24/2018.
 */
public enum UserType {

    company("company"),
    school("school"),
    admin("admin");

    private String value;

    private UserType(String value) {
        this.value = value;
    }

    public static UserType fromValue(String value) {
        for (UserType category : values()) {
            if (category.value.equalsIgnoreCase(value)) {
                return category;
            }
        }
        throw new IllegalArgumentException(
                "Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
    }
}
