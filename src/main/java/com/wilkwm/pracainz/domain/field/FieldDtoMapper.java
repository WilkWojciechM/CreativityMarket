package com.wilkwm.pracainz.domain.field;

import com.wilkwm.pracainz.domain.field.dto.FieldDto;

public class FieldDtoMapper {
    static FieldDto map(Field field) {
        return new FieldDto(
                field.getId(),
                field.getName(),
                field.getDescription()
        );
    }
}
