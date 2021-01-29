package ftn.isa.pharmacy.controller;

import java.util.ArrayList;
import java.util.List;

import ftn.isa.pharmacy.model.SysAdmin;
import ftn.isa.pharmacy.service.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class LogInController {

    @Autowired
    private LogInService logInService;


}
