package com.greem.project.mapper;

import com.greem.project.domain.child.Child;
import com.greem.project.domain.child.ChildDto;
import com.greem.project.domain.child.Gender;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ChildMapper {

    public ChildDto mapToChildDto(Child child) {
        LocalDate dateOfBirth = child.getDateOfBirth();
        return new ChildDto(
            child.getId(),
            child.getFirstName(),
            child.getLastName(),
            dateOfBirth.getDayOfMonth() + "-" + dateOfBirth.getMonthValue() + "-" + dateOfBirth.getYear(),
            child.getGender().getAbbreviation()
        );
    }

    public Child mapToChild(ChildDto childDto) {
        List<Integer> dateOfBirth = Stream.of(childDto.getDateOfBirth().split("-"))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        return new Child(
                childDto.getId(),
                childDto.getFirstName(),
                childDto.getLastName(),
                LocalDate.of(dateOfBirth.get(2), dateOfBirth.get(1), dateOfBirth.get(0)),
                Gender.from(childDto.getGender())
        );
    }

    public List<ChildDto> mapToChildDtoList(List<Child> childList) {
        return childList.stream()
                .map(this::mapToChildDto)
                .collect(Collectors.toList());
    }
}
