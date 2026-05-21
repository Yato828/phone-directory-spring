package com.yato.phoneDirectorySpring.controller;

import com.yato.phoneDirectorySpring.entity.Contact;
import com.yato.phoneDirectorySpring.entity.PhoneNumber;
import com.yato.phoneDirectorySpring.repository.PhoneNumberRepository;
import com.yato.phoneDirectorySpring.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Controller
@RequestMapping("/contacts")
public class ContactController {


    @Autowired
    private ContactService contactService;

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @PersistenceContext
    private EntityManager entityManager;

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
    public String deleteContact(@RequestParam int id) {
        contactService.delete(id);
        return "redirect:/contacts/all";
    }

    @GetMapping("/phones/{contactId}")
    public String showPhones(@PathVariable int contactId, Model model) {
        Contact contact = contactService.getById(contactId);
        List<PhoneNumber> phones = phoneNumberRepository.findByContactId(contactId);
        model.addAttribute("contact", contact);
        model.addAttribute("phones", phones);
        return "phones";
    }

    @GetMapping("/phones/add/{contactId}")
    public String showAddPhoneForm(@PathVariable int contactId, Model model) {
        model.addAttribute("contactId", contactId);
        model.addAttribute("phone", new PhoneNumber());
        return "phone-form";
    }

    @PostMapping("/phones/save")
    public String savePhone(@RequestParam int contactId,
                            @RequestParam String number,
                            @RequestParam String type) {
        Contact contact = contactService.getById(contactId);
        PhoneNumber phone = new PhoneNumber(number, type, contact);
        phoneNumberRepository.save(phone);
        return "redirect:/contacts/phones/" + contactId;
    }

    @GetMapping("/phones/delete/{phoneId}")
    public String deletePhone(@PathVariable int phoneId) {
        PhoneNumber phone = entityManager.find(PhoneNumber.class, phoneId);
        int contactId = phone.getContact().getId();
        phoneNumberRepository.deleteByContactId(contactId);
        return "redirect:/contacts/phones/" + contactId;
    }
}
