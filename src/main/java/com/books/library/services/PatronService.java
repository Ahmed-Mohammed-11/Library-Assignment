package com.books.library.services;

import com.books.library.daos.implementations.PatronRepositoryImplementation;
import com.books.library.daos.mappers.PatronMapper;
import com.books.library.dtos.PatronDTO;
import com.books.library.entities.Patron;
import com.books.library.exceptions.exceptions.PatronNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.books.library.constants.ResponseMessageConstants.PATRON_NOT_FOUND;

@Service
@AllArgsConstructor
public class PatronService {

    private PatronRepositoryImplementation patronRepositoryImplementation;
    private PatronMapper patronMapper;

    public List<PatronDTO> getAllPatrons() {
        List<Patron> patrons = patronRepositoryImplementation.findAll();
        if (patrons.isEmpty())
            throw new PatronNotFoundException(PATRON_NOT_FOUND);
        return patrons.stream().map(patronMapper::toPatronDTO).toList();
    }

    public PatronDTO getPatronById(Integer PatronId) {
        Patron patron = patronRepositoryImplementation.findById(PatronId).orElseThrow(() -> new PatronNotFoundException(PATRON_NOT_FOUND));
        return patronMapper.toPatronDTO(patron);
    }

    public void insertPatron(PatronDTO patron) {
        Patron patronEntity = patronMapper.toPatron(patron);
        patronRepositoryImplementation.insert(patronEntity);
    }

    public void updatePatron(Integer PatronId, PatronDTO Patron) {
        patronRepositoryImplementation.findById(PatronId).orElseThrow(() -> new PatronNotFoundException(PATRON_NOT_FOUND));
        Patron PatronEntity = patronMapper.toPatron(Patron);
        PatronEntity.setId(PatronId);
        patronRepositoryImplementation.update(PatronEntity);
    }

    public void deletePatron(Integer PatronId) {
        patronRepositoryImplementation.findById(PatronId).orElseThrow(() -> new PatronNotFoundException(PATRON_NOT_FOUND));
        patronRepositoryImplementation.deleteById(PatronId);
    }
}

