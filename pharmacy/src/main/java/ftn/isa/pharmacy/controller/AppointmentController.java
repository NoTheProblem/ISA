package ftn.isa.pharmacy.controller;

import ftn.isa.pharmacy.model.WorkingHours;
import ftn.isa.pharmacy.repository.WorkingHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;

@RestController
@RequestMapping("/auth")
public class AppointmentController {

    private final WorkingHoursRepository workingHoursRepository;

    @Autowired
    public AppointmentController(WorkingHoursRepository workingHoursRepository) {
        this.workingHoursRepository = workingHoursRepository;
    }

    @PostMapping("/addPromotion")
    public void addAllergy() {
        WorkingHours workingHours = new WorkingHours();
        long now = System.currentTimeMillis();
        Time sqlTime = new Time(now);
        workingHours.setId((long) 1);
        workingHours.setStartTime(sqlTime);
        workingHours.setEndTime(sqlTime);
        System.out.println(sqlTime);
        workingHoursRepository.save(workingHours);
        System.out.println(workingHours);
    }

/*

    @GetMapping(value = "/getAllActive")
    public ResponseEntity<Collection<>> getAllActive() {

        return null;
    }
*/


}
