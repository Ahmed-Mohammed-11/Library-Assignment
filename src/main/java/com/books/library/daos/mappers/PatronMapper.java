package com.books.library.daos.mappers;

import com.books.library.dtos.PatronDTO;
import com.books.library.entities.Patron;
import org.springframework.stereotype.Component;

@Component
public class PatronMapper {
    public Patron toPatron(PatronDTO patronDTO) {
        return Patron.builder()
                .name(patronDTO.getName())
                .contactInformation(patronDTO.getContactInformation())
                .build();
    }

    public PatronDTO toPatronDTO(Patron patron) {
        return PatronDTO.builder()
                .name(patron.getName())
                .contactInformation(patron.getContactInformation())
                .build();
    }
}
