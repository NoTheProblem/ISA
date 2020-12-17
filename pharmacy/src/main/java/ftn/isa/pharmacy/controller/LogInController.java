package ftn.isa.pharmacy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ftn.isa.pharmacy.model.Sysadmin;
import ftn.isa.pharmacy.service.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogInController {

    @Autowired
    private LogInService logInService;


    @GetMapping(value = "/all")
    public ResponseEntity<List<Sysadmin>> getAllStudents() {
        System.out.println("pozdrav iz contorlera");
        List<Sysadmin> users = logInService.findAll();

        // convert students to DTOs
        List<Sysadmin> usersDTO = new ArrayList<Sysadmin>();
        for (Sysadmin u : users) {
            usersDTO.add(new Sysadmin(u));
        }

        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }


}
