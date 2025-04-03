package com.example.demo.Library;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatronServiceTest {

    @Autowired
    private PatronRepository patronRepository;


    private  PatronService patronService;

    private Patron patron;


    @Test
    void getAllPatrons() {
        Patron patron = new Patron();
        patron.setId(1L);
        patron.setName("farag");
        patron.setContactInfo("01212");

        patronRepository.save(patron);
        List<Patron> patrons = patronRepository.findAll();
        assertNotEquals(patrons.size() , 0);
    }

    @Test
    void getPatronById() {
        Patron patron = new Patron();
        patron.setId(1L);
        patron.setName("farag");
        patron.setContactInfo("01212");

        patronRepository.save(patron);

        Patron p = patronRepository.findById(1L).get();
        assertEquals(patron.getContactInfo(), "01212");
    }

    @Test
    void addPatron() {
        Patron patron = new Patron();
        patron.setId(1L);
        patron.setName("farag");
        patron.setContactInfo("01212");

        patronRepository.save(patron);

        assertNotNull(patronRepository.findById(1L).get());
    }

    @Test
    void updatePatron() {
        Patron patron = new Patron();
        patron.setId(1L);
        patron.setName("farag");
        patron.setContactInfo("01212");

        patronRepository.save(patron);
        Patron b = patronRepository.findById(1L).get();
        b.setName("asd");
        patronRepository.save(b);
        assertNotEquals("2002", patronRepository.findById(1L).get().getName());
    }

    @Test
    void deletePatron() {
        Patron patron = new Patron();
        patron.setId(1L);
        patron.setName("farag");
        patron.setContactInfo("01212");

        patronRepository.save(patron);
        patronRepository.deleteById(1L);
        assertFalse(patronRepository.existsById(1L));
    }
}