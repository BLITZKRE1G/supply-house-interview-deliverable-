package abhinab.full_stack.spring_assignment.controllers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import abhinab.full_stack.spring_assignment.model.SupportSchedule;
import abhinab.full_stack.spring_assignment.model.SupportScheduleDTO;
import abhinab.full_stack.spring_assignment.services.SupportScheduleService;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequestMapping(path = "/support-schedule")
@CrossOrigin(origins = "http://localhost:3000")
public class SupportController {
    private final SupportScheduleService service;

    public SupportController(SupportScheduleService service) {
        this.service = service;
    }

    @GetMapping(path = "/{requested-date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SupportScheduleDTO> getSchedule(@PathVariable("requested-date") LocalDate date) {
        try {
            return new ResponseEntity<SupportScheduleDTO>(service.getSupportTime(date), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<SupportScheduleDTO>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/create-schedule", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createSchedule(@RequestBody SupportSchedule schedule) {
        try {
            return new ResponseEntity<SupportScheduleDTO>(service.createSchedule(schedule), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("error: ", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SupportScheduleDTO> login() {
        try {
            return new ResponseEntity<>(service.login(), HttpStatus.ACCEPTED);
        } catch (Exception exc) {
            exc.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/default-schedule/{req-day}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<SupportScheduleDTO> getScheduleByDay(@PathVariable("req-day") @Validated DayOfWeek day) {
        try {
            return new ResponseEntity<SupportScheduleDTO>(service.getByDay(day), HttpStatus.FOUND);
        } catch (Exception exc) {
            log.error("exception-message: {}", exc.getMessage());
            return new ResponseEntity<SupportScheduleDTO>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/all-priority-schedules", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<SupportScheduleDTO>> getAllPrioritySchedules() {
        try {
            return new ResponseEntity<>(service.getAllPrioritySchedules(), HttpStatus.OK);
        } catch (Exception exc) {
            exc.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
