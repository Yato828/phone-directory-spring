package com.yato.phoneDirectorySpring.controller;

import com.yato.phoneDirectorySpring.entity.Contact;
import com.yato.phoneDirectorySpring.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Controller
@RequestMapping("/all")
public class ContactController {

    @Autowired
    private ContactRepository repository;

    @GetMapping
        public String listContacts (Model model){
            List<Contact> contacts = repository.findAll();
            model.addAttribute("contacts", contacts);
            return "pages/contacts";

        }
    }
