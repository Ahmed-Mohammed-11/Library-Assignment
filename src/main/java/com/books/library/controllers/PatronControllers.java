package com.books.library.controllers;

import com.books.library.dtos.PatronDTO;
import com.books.library.services.PatronService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.books.library.constants.ApiConstants.API_PATRONS;
import static com.books.library.constants.ResponseMessageConstants.*;

@RestController
@RequestMapping(API_PATRONS)
@AllArgsConstructor
public class PatronControllers {

    private PatronService patronService;

    @GetMapping("/")
    public ResponseEntity<List<PatronDTO>> getAllPatrons() {
        List<PatronDTO> allPatrons = patronService.getAllPatrons();
        return ResponseEntity.status(HttpStatus.OK).body(allPatrons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPatronById(@PathVariable int id) {
        PatronDTO patron = patronService.getPatronById(id);
        return ResponseEntity.status(HttpStatus.OK).body(patron);
    }

    @PostMapping
    public ResponseEntity<String> addPatron(@Valid @RequestBody PatronDTO patron) {
        patronService.insertPatron(patron);
        return ResponseEntity.status(HttpStatus.CREATED).body(PATRON_ADDED_SUCCESSFULLY);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePatron(@PathVariable int id, @Valid @RequestBody PatronDTO patron) {
        Integer patronId = id;
        patronService.updatePatron(patronId, patron);
        return ResponseEntity.status(HttpStatus.OK).body(PATRON_UPDATED_SUCCESSFULLY);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatron(@PathVariable int id) {
        Integer patronId = id;
        patronService.deletePatron(patronId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(PATRON_DELETED_SUCCESSFULLY);
    }
}
