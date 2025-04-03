package com.example.demo.Library;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BorrowingRecordService {

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;

    @Transactional
    public  BorrowingRecord borrowBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        Patron patron = patronRepository.findById(patronId).orElseThrow();

         BorrowingRecord record = new BorrowingRecord();
         record.setBook(book);
         record.setPatron(patron);
         record.setBorrowDate(LocalDate.now());

         return borrowingRecordRepository.save(record);

    }

    @Transactional
    public BorrowingRecord returnBook(Long bookId, Long patronId) {
        BorrowingRecord record = borrowingRecordRepository.findAll().stream()
                .filter(r-> r.getBook().getId().equals(bookId) && r.getPatron().getId().equals(patronId) && r.getReturnDate() == null)
                .findFirst().orElseThrow();

        record.setReturnDate(LocalDate.now());
        return borrowingRecordRepository.save(record);

    }

}
