package com.greem.project.domain.child;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String> {
    @Override
    public String convertToDatabaseColumn(Gender gender) {
        if (gender == null) {
            return null;
        }
        return gender.getAbbreviation();
    }

    @Override
    public Gender convertToEntityAttribute(String abbreviation) {
        if (abbreviation == null) {
            return null;
        }
        return Stream.of(Gender.values())
                .filter(e -> e.getAbbreviation().equals(abbreviation))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
