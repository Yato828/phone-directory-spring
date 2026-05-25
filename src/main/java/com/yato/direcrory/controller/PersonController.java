package com.yato.direcrory.controller;

import com.yato.direcrory.entity.Person;
import com.yato.direcrory.entity.PhoneNumber;
import com.yato.direcrory.service.PersonService;
import com.yato.direcrory.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PhoneService phoneService;

    @GetMapping("/all")
    public String getAllPersons(Model model) {
        model.addAttribute("persons", personService.getAll());
        return "persons";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("person", new Person());
        return "person";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam int id, Model model) {
        model.addAttribute("person", personService.getById(id));
        return "person";
    }

    @PostMapping("/save")
    public String savePerson(@RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam(required = false) String middleName,
                             @RequestParam(required = false) String phone,
                             @RequestParam(required = false) String birthDate) {

        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setMiddleName(middleName);
        person.setBirth(birthDate);
        personService.saveOrUpdate(person);

        // Если передан телефон, создаем номер
        if (phone != null && !phone.isEmpty()) {
            PhoneNumber phoneNumber = new PhoneNumber();
            phoneNumber.setNumber(phone);
            phoneNumber.setType("MAIN");
            phoneNumber.setPerson(person);
            phoneService.save(phoneNumber);
        }

        return "redirect:/persons/all";
    }

    @PostMapping("/update")
    public String updatePerson(@RequestParam int id,
                               @RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam(required = false) String middleName,
                               @RequestParam(required = false) String birthDate) {

        Person person = personService.getById(id);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setMiddleName(middleName);
        person.setBirth(birthDate);
        personService.saveOrUpdate(person);

        return "redirect:/persons/all";
    }

    @PostMapping("/delete")
    public String deletePerson(@RequestParam int id) {
        phoneService.deleteByPersonId(id);
        personService.delete(id);
        return "redirect:/persons/all";
    }
}