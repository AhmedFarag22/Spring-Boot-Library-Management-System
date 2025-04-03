package com.example.demo.Library;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService {

    @Autowired
    private PatronRepository patronRepository;

    public List<Patron> getAllPatrons(){
        return patronRepository.findAll();
    }

    public Optional<Patron> getPatronById(Long id){
        return patronRepository.findById(id);
    }

    @Transactional
    public Patron addPatron(Patron patron){
        return patronRepository.save(patron);
    }

    @Transactional
    public Patron updatePatron(Long id, Patron patron){
        patron.setId(id);
        return patronRepository.save(patron);
    }

    @Transactional
    public void deletePatron(Long id){
        boolean exists = patronRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("student with id "+ id +
                    " does not exist");
        }
        patronRepository.deleteById(id);
    }


}
