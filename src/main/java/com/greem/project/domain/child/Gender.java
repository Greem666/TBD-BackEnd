package com.greem.project.domain.child;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("M"), FEMALE("F"), OTHER("O");

    private String abbreviation;

    Gender(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public static Gender from(String text) {
        for (Gender gender : Gender.values()) {
            if (gender.abbreviation.equalsIgnoreCase(text)) {
                return gender;
            }
        }
        return null;
    }
}
