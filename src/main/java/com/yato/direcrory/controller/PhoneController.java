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
@RequestMapping("/persons")
public class PhoneController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PhoneService phoneService;

    @GetMapping("/phones/{personId}")
    public String showPhones(@PathVariable int personId, Model model) {
        Person person = personService.getById(personId);
        List<PhoneNumber> phones = phoneService.findByPersonId(personId);
        model.addAttribute("person", person);
        model.addAttribute("phones", phones);
        return "phones";
    }

    @GetMapping("/phones/add/{personId}")
    public String showAddPhoneForm(@PathVariable int personId, Model model) {
        model.addAttribute("personId", personId);
        model.addAttribute("phone", new PhoneNumber());
        return "phone";
    }

    @PostMapping("/phones/save")
    public String savePhone(@RequestParam int personId,
                            @RequestParam String number,
                            @RequestParam String type) {
        Person person = personService.getById(personId);
        PhoneNumber phone = new PhoneNumber(number, type, person);
        phoneService.save(phone);
        return "redirect:/persons/phones/" + personId;
    }

    @GetMapping("/phones/delete/{phoneId}")
    public String deletePhone(@PathVariable int phoneId) {
        PhoneNumber phone = phoneService.findById(phoneId);
        int personId = phone.getPerson().getId();
        phoneService.deleteById(phoneId);
        return "redirect:/persons/phones/" + personId;
    }
}