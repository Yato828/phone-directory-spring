package com.yato.direcrory.controller;

import com.yato.direcrory.entity.Person;
import com.yato.direcrory.entity.PhoneNumber;
import com.yato.direcrory.service.PersonService;
import com.yato.direcrory.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/phones")
public class PhoneController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PhoneService phoneService;

    @GetMapping("/{personId}")
    public String showPhones(@PathVariable int personId, Model model) {
        Person person = personService.getById(personId);
        List<PhoneNumber> phones = phoneService.findByPersonId(personId);
        model.addAttribute("person", person);
        model.addAttribute("phones", phones);
        return "phones";
    }

    @GetMapping("/add/{personId}")
    public String showAddPhoneForm(@PathVariable int personId, Model model) {
        model.addAttribute("personId", personId);
        model.addAttribute("phone", new PhoneNumber());
        return "phone";
    }

    @GetMapping("/edit/{phoneId}")
    public String showEditPhoneForm(@PathVariable int phoneId, Model model) {
        PhoneNumber phone = phoneService.findById(phoneId);
        model.addAttribute("phone", phone);
        model.addAttribute("personId", phone.getPerson().getId());
        return "phone";
    }

    @PostMapping("/save")
    public String savePhone(@ModelAttribute PhoneNumber phone,
                            @RequestParam int personId) {
        if (phone.getId() == null) {
            Person person = personService.getById(personId);
            phone.setPerson(person);
        }
        phoneService.saveOrUpdate(phone);
        return "redirect:/phones/" + personId;
    }

    @GetMapping("/delete/{phoneId}")
    public String deletePhone(@PathVariable int phoneId) {
        PhoneNumber phone = phoneService.findById(phoneId);
        int personId = phone.getPerson().getId();
        phoneService.deleteById(phoneId);
        return "redirect:/phones/" + personId;
    }
}