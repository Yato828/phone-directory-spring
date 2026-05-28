package com.yato.direcrory.controller;

import com.yato.direcrory.entity.Person;
import com.yato.direcrory.service.PersonService;
import com.yato.direcrory.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
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
                             @RequestParam String middleName,
                             @RequestParam String birth,
                             Model model) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date birthDate = null;
        try {
            if (birth != null && !birth.isEmpty()) {
                birthDate = formatter.parse(birth);
            }
        } catch (Exception e) {
            birthDate = null;
        }
        Person person = new Person();
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

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String query, Model model) {
        List<Person> persons = personService.search(query);
        model.addAttribute("persons", persons);
        return "persons";
    }
}