package com.yato.phoneDirectorySpring.controller;

import com.yato.phoneDirectorySpring.entity.Contact;
import com.yato.phoneDirectorySpring.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService service;

    @GetMapping("/all")
    public String listContacts(Model model) {
        List<Contact> contacts = service.getAll();
        model.addAttribute("contacts", contacts);
        return "pages/contacts";
    }
    @GetMapping("/edit")
    public String editForm(@RequestParam int id, Model model)  {
    Contact contact = service.getById(id);
    model.addAttribute("contact", contact);
    return "pages/contact";
    }

    @PostMapping("/update")
    public String update (@RequestParam int id,
                          @RequestParam String firstName,
                          @RequestParam String lastName,
                          @RequestParam String middleName,
                          @RequestParam String phone,
                          @RequestParam String birthDate){

        Contact contact = new Contact();
        contact.setId(id);
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setMiddleName(middleName);
        contact.setPhone(phone);
        contact.setBirth(birthDate);

        service.update(contact);
        return "redirect:/contacts/all";
    }

    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("contact", new Contact());
        return "pages/contact";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Contact contact){
        service.save(contact);
        return "redirect:/contacts/all";
    }
    @PostMapping("/delete")
    public String delete(@RequestParam int id){
        service.delete(id);
        return "redirect:/contacts/all";
    }

}