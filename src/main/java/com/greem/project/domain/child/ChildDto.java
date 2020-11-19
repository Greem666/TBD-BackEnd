package com.greem.project.domain.child;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChildDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;

    public ChildDto(String firstName, String lastName, String dateOfBirth, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }
}
