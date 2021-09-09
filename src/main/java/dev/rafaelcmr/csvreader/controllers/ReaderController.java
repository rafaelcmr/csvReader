package dev.rafaelcmr.csvreader.controllers;

import dev.rafaelcmr.csvreader.model.User;
import dev.rafaelcmr.csvreader.service.CSVReaderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class ReaderController {

    private final CSVReaderService reader;

    public ReaderController(CSVReaderService reader) {
        this.reader = reader;
    }

    @GetMapping
    public ResponseEntity<Page<User>> getUser() {
        List<User> userList = reader.readUsersFromCSV("database.csv");
        Pageable pageable = PageRequest.of(0, 15);
        Page<User> page = new PageImpl<>(userList, pageable, 15);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}
