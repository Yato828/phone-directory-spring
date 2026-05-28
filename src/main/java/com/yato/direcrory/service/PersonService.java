package com.yato.direcrory.service;

import com.yato.direcrory.entity.Person;
import com.yato.direcrory.repository.PersonRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    @Transactional(readOnly = true)
    public List<Person> getAll() {
        List<Person> persons = repository.findAll();
        for (Person person : persons) {
            Hibernate.initialize(person.getPhoneNumbers());
        }
        return persons;
    }

    @Transactional(readOnly = true)
    public Person getById(int id) {
        Person person = repository.findById(id);
        if (person != null) {
            Hibernate.initialize(person.getPhoneNumbers());
        }
        return person;
    }

    @Transactional
    public void saveOrUpdate(Person person) {
        if (person.getId() == null) {
            repository.save(person);
        } else {
            repository.update(person);
        }
    }

    @Transactional
    public void delete(int id) {
        repository.delete(id);
    }
    private boolean isPhoneNumber(String text) {
        String onlyDigits = text.replaceAll("[\\s\\-+()]", "");
        return !onlyDigits.isEmpty() && onlyDigits.matches("^\\d+$");
    }


    @Transactional(readOnly = true)
    public List<Person> search(String query) {
        List<Person> persons;
        if (query == null || query.isEmpty()) {
            persons = repository.findAll();
        }
        else if (isPhoneNumber(query)) {
            persons = repository.searchByNumber(query);
        }
        else {
            persons = repository.search(query);
        }
        for (Person person : persons) {
            Hibernate.initialize(person.getPhoneNumbers());
        }
        return persons;
    }

}

