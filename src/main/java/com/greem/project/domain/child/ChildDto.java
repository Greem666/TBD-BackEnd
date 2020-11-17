package com.greem.project.domain.child;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChildDto {
    private Long id;
    private String fullName;
    private String dateOfBirth;
    private String gender;

    public ChildDto(String fullName, String dateOfBirth, String gender) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }
}
