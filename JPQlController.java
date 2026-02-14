package com.example.DDamo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class JPQlController {

    @Autowired
    PersonsJPQLRepositry personsJPQLRepositry;
    @GetMapping("/all")
    public String all(Model model)
    {
        List<Persons> lst=personsJPQLRepositry.getAllPersons();
        model.addAttribute("data",lst);

        return "Showp";
    }

    @GetMapping("/searchstate")
    public String Showstate()
    {
        return "searchstate";
    }

    @PostMapping("/showstate")
    public String allwhere(Model model,String state)
    {
            List<Persons> lst=personsJPQLRepositry.findByState(state);

                model.addAttribute("data", lst);
                return "Showp";

    }

    @GetMapping("/updatestate")
    public String Updatestate( Model model)
    {
        List<Persons> lst=personsJPQLRepositry.getAllPersons();
        model.addAttribute("list",lst);
        return "updatestate";
    }

    @PostMapping("/update")
    @ResponseBody
    public  String Parsonsupdate( String state,int id,Model model)
    {
         personsJPQLRepositry.updatestate(state,id);
         return "update successfull";


    }

    @GetMapping("/deletepersons")
    public String Deletepersons(Model model)
    {
        List<Persons> lst=personsJPQLRepositry.getAllPersons();
        model.addAttribute("delete",lst);
        return "deletepersons";
    }
    @PostMapping("/delete")
    public String pdelete( int id,Model model)
    {
        personsJPQLRepositry.deleteById(id);
        return "deleteconfirm";
    }

}
