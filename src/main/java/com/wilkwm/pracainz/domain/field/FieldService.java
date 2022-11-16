package com.wilkwm.pracainz.domain.field;

import com.wilkwm.pracainz.domain.field.dto.FieldDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class FieldService {
    private final FieldRepository fieldRepository;

    public FieldService(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    public Optional<FieldDto> findFieldByName(String name){
        return fieldRepository.findByNameIgnoreCase(name)
                .map(FieldDtoMapper::map);
    }

    public List<FieldDto> findAllFields(){
        return StreamSupport.stream(fieldRepository.findAll().spliterator(), false)
                .map(FieldDtoMapper::map)
                .toList();
    }

    //metoda, przyjmuje obiekt FieldDto i tworzy na jego podstawie obiekt Field oraz zapisuje w bazie
    @Transactional
    public void addField(FieldDto field){
        Field fieldSave = new Field();
        fieldSave.setName(field.getName());
        fieldSave.setDescription(field.getDescription());
        fieldRepository.save(fieldSave);
    }
}
