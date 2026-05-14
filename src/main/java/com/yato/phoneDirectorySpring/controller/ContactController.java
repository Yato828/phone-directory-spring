package com.yato.phoneDirectorySpring.controller;

import com.yato.phoneDirectorySpring.entity.Contact;
import com.yato.phoneDirectorySpring.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService service;

    @GetMapping
    public String listContacts(Model model) {
        List<Contact> contacts = service.getAll();
        model.addAttribute("contacts", contacts);
        return "pages/contacts";
    }
}