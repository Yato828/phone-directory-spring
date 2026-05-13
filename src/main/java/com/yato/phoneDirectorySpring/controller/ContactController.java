package com.yato.phoneDirectorySpring.controller;

import com.yato.phoneDirectorySpring.entity.Contact;
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

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping
    public String listContacts(Model model) {
        List<Contact> contacts = entityManager
                .createQuery("FROM Contact", Contact.class)
                .getResultList();

        model.addAttribute("contacts", contacts);
        return "pages/contacts";
    }
}