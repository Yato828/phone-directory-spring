package com.yato.phoneDirectorySpring.controller;

import com.yato.phoneDirectorySpring.entity.Contact;
import com.yato.phoneDirectorySpring.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/all")
    public String getAllContacts(Model model) {
        model.addAttribute("contacts", contactService.getAll());
        return "contacts";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam int id, Model model) {  // Long -> int
        model.addAttribute("contact", contactService.getById(id));
        return "contact";
    }

    @PostMapping("/save")
    public String saveContact(@ModelAttribute Contact contact) {
        contactService.save(contact);
        return "redirect:/contacts/all";
    }

    @PostMapping("/update")
    public String updateContact(@ModelAttribute Contact contact) {
        contactService.update(contact);
        return "redirect:/contacts/all";
    }

    @PostMapping("/delete")
    public String deleteContact(@RequestParam int id) {  // Long -> int
        contactService.delete(id);
        return "redirect:/contacts/all";
    }
}