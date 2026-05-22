package com.yato.direcrory.service;

import com.yato.direcrory.entity.Person;
import com.yato.direcrory.entity.PhoneNumber;
import com.yato.direcrory.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository repository;

    public List<Person> getAll() {
        return repository.findAll();
    }

    public Person getById(int id) {
        return repository.findById(id);
    }
    public void edit(Person person){
        repository.edit(person);
    }

    @Transactional
    public void saveOrUpdate(Person person) {
        repository.save(person);
    }
    @Transactional
    public void delete(int id) {
        repository.delete(id);
    }

}


