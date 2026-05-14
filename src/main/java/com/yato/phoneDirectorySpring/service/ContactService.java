package com.yato.phoneDirectorySpring.service;

import com.yato.phoneDirectorySpring.entity.Contact;
import com.yato.phoneDirectorySpring.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    @Autowired
    private ContactRepository repository;


    public List<Contact> getAll() {
    return repository.getAll();

    }
}


