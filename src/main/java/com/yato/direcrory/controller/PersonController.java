package com.yato.direcrory.controller;

import com.yato.direcrory.entity.Person;
import com.yato.direcrory.entity.PhoneNumber;
import com.yato.direcrory.repository.PhoneNumberRepository;
import com.yato.direcrory.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/persons")
public class PersonController {


    @Autowired
    private PersonService personService;

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @GetMapping("/all")
    public String getAllPersons(Model model) {
        model.addAttribute("persons", personService.getAll());
        return "persons";
    }

    @GetMapping("/add")
    public String showAddForm( Model model) {
        model.addAttribute("person", new Person());
        return "person";
    }
    @GetMapping("/phones/add/{personId}")
    public String showAddPhoneForm(@PathVariable int personId, Model model) {
        model.addAttribute("personId", personId);
        model.addAttribute("phone", new PhoneNumber());
        return "phone";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam int id, Model model) {
        model.addAttribute("person", personService.getById(id));
        return "person";
    }

    @PostMapping("/save")
    public String savePerson(@ModelAttribute Person person) {
        personService.save(person);
        return "redirect:/persons/all";
    }

    @PostMapping("/update")
    public String updatePerson(@ModelAttribute Person person) {
        personService.update(person);
        return "redirect:/persons/all";
    }

    @PostMapping("/delete")
    public String deletePerson(@RequestParam int id) {
        personService.delete(id);
        return "redirect:/persons/all";
    }

    @PostMapping("/phones/save")
    public String savePhone(@RequestParam int personId,
                            @RequestParam String number,
                            @RequestParam String type) {
        Person person = personService.getById(personId);
        PhoneNumber phone = new PhoneNumber(number, type, person);
        phoneNumberRepository.save(phone);
        return "redirect:/persons/phones/" + personId;
    }

    @GetMapping("/phones/delete/{phoneId}")
    public String deletePhone(@PathVariable int phoneId) {
        PhoneNumber phone = phoneNumberRepository.findById(phoneId);
        int personId = phone.getPerson().getId();
        phoneNumberRepository.deleteById(phoneId);
        return "redirect:/persons/phones/" + personId;
    }

    @GetMapping("/phones/{personId}")
    public String showPhones(@PathVariable int personId, Model model) {
        Person person = personService.getById(personId);
        List<PhoneNumber> phones = phoneNumberRepository.findByPersonId(personId);
        model.addAttribute("person", person);
        model.addAttribute("phones", phones);
        return "phones";
    }
}
