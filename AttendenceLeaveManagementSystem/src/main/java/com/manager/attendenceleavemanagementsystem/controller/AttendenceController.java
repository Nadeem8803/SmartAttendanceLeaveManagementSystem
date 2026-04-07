package com.manager.attendenceleavemanagementsystem.controller;

import com.manager.attendenceleavemanagementsystem.dto.AttendenceRequestDTO;
import com.manager.attendenceleavemanagementsystem.dto.AttendenceResponseDTO;
import com.manager.attendenceleavemanagementsystem.entity.Attendence;
import com.manager.attendenceleavemanagementsystem.service.AttendenceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendence")
public class AttendenceController {

    private AttendenceService attendenceService;

    AttendenceController(AttendenceService attendenceService){
        this.attendenceService = attendenceService;
    }

//    @PostMapping
//    public Attendence markAttendence(@RequestBody Attendence attendence){
//        return attendenceService.markAttendenec(attendence);
//    }

    @PostMapping
    public AttendenceResponseDTO markAttendence(@RequestBody AttendenceRequestDTO attendenceRequestDTO){
        return attendenceService.markAttendence(attendenceRequestDTO);
    }

    @GetMapping("/{attendenceId}")
    public List<Attendence> getAttendenceById(@PathVariable Long attendenceId){
        return attendenceService.getAttendenceByEmployeeId(attendenceId);
    }

    @GetMapping
    public List<Attendence> getAttendence(){
        return attendenceService.getAllAttendence();
    }

    @PutMapping("/checkOut/{employeeId}")
    public Attendence checkOut(@PathVariable Long employeeId){
        return attendenceService.checkOut(employeeId);
    }


}
