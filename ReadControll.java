package com.example.DDamo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ReadControll {

    @Autowired
    PersonsRepositry personsRepositry;
    @GetMapping("/showperson")
    public String Showperson(Model model)
    {
        List<Persons> lst=new ArrayList<>();
        personsRepositry.findAll().forEach(lst::add);

        model.addAttribute("plist",lst);
        return "showrecords";
    }

    @GetMapping("/findbyid/{srno}")
    public ResponseEntity<Persons> findbyid(@PathVariable int srno)
    {
        Optional<Persons> pers=personsRepositry.findById(srno);

        if(pers.isPresent())
            return ResponseEntity.ok(pers.get());
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/findfirstbystate/{state}")
    public ResponseEntity<Persons> findbyname(@PathVariable String state)
    {
        Optional<Persons> pers=personsRepositry.findFirstByState(state);
        if(pers.isPresent())
            return ResponseEntity.ok(pers.get());
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/findbystate/{state}")
    @ResponseBody
    public List<Persons> findbystate(@PathVariable String state)
    {
        List<Persons> lst=new ArrayList<>();
        personsRepositry.findByState(state).forEach(lst::add);

        return lst ;
    }

    @GetMapping("/updateperson/{id}")
    public String Updateperson(@PathVariable int id)
    {
        Optional<Persons> opt=personsRepositry.findById(id);

        if (opt.isPresent())
        {
            Persons p=opt.get();
            p.setName("Raj");
            p.setGender("Male");
            p.setState("Rajesthan");
            personsRepositry.save(p);
            return "confirm";

        }
        else
            return "Notfound";
    }

   @GetMapping("/updateperson")
    public String Showallids(Model model)
   {
       List<Persons> lst=new ArrayList<>();
       personsRepositry.findAll().forEach(lst::add);
       model.addAttribute("list",lst);
       return "PersonsUpdate";

   }

   @PostMapping("/dataupdate")
    public String Dataupdate(int id, String name, String gen, String state, LocalDate doj, Model model)
   {

           Optional<Persons> opt=personsRepositry.findById(id);
           if(opt.isPresent())
           {
               Persons p = opt.get();

               p.setName(name);
               p.setGender(gen);
               p.setState(state);
               p.setDoj(doj);
               personsRepositry.save(p);
               return "PUS";
           }
           else
              return "Notfound";

   }


}
