package com.example.cruddemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
// det tog 27 minutter inklusive stop på vejen for at finde fejl og google.
@Controller
public class HomeController {

    List<Person> personList = new ArrayList<>();
    @RequestMapping("/")
    public String getIndex(Model model){
        model.addAttribute("persons", personList);
        return "index";
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String addUser(Model model, Person person){
        person.setId(personList.size());
        personList.add(person);
        model.addAttribute("persons", personList);
        System.out.println("received a Person object for create" + person.getUsername());
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = "edituser")
    public String editUser(Model model, Person person){
        personList.remove(getPersonById(person.getId()));
        personList.add(person);
        model.addAttribute("persons", personList);
        System.out.println("received a Person object for edit" + person.getUsername());
        return "index";
    }

    private Person getPersonById(int id){
        for (Person person : personList) {
            if(person.getId() == id)
            {
                return person;
            }
        }
        return null;
    }
}
