package com.yato.phoneDirectorySpring.service;

import com.yato.phoneDirectorySpring.entity.Contact;
import com.yato.phoneDirectorySpring.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ContactService {
    @Autowired
    private ContactRepository repository;


    public List<Contact> getAll() {
        return repository.findAll();
    }

    public Contact getById(int id) {
        return repository.findById(id);
    }
    public void edit(Contact contact){
        repository.edit(contact);
    }
    @Transactional
    public void update(Contact contact) {
        repository.update(contact);
    }
    @Transactional
    public void save(Contact contact) {
        repository.save(contact);
    }
    @Transactional
    public void delete(int id) {
        repository.delete(id);
    }
}


