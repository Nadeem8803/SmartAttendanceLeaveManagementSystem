package com.manager.attendenceleavemanagementsystem.controller;

import com.manager.attendenceleavemanagementsystem.dto.LeaveRequestDTO;
import com.manager.attendenceleavemanagementsystem.dto.LeaveResponseDTO;
import com.manager.attendenceleavemanagementsystem.entity.Employee;
import com.manager.attendenceleavemanagementsystem.entity.Leave;
import com.manager.attendenceleavemanagementsystem.service.LeaveService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/leave")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @PostMapping
    public LeaveResponseDTO applyLeave(@RequestBody LeaveRequestDTO leaveRequestDTO){
        return leaveService.applyLeave(leaveRequestDTO);
    }

    @GetMapping
    public List<Leave> getAllLeaves(){
        return leaveService.getAllLeaves();
    }

    @GetMapping("/{leaveId}")
    public Leave getLeaveById(@PathVariable Long leaveId){
        return leaveService.getLeaveById(leaveId);
    }

    @GetMapping("/employee/{employeeId}")
    public List<Leave> getLeaveByEmployeeId(@PathVariable Long employeeId){
        return leaveService.getLeaveByEmployeeId(employeeId);
    }

    @DeleteMapping("/{leaveId}")
    public void deleteLeaveById( @RequestParam Long loggedInEmployeeId,@PathVariable Long leaveId){
        Leave leave = leaveService.getLeaveById(leaveId);
        leaveService.deleteLeaveById(loggedInEmployeeId,leaveId);
    }

    @PutMapping("/status/{leaveId}")
    public void updateLeaveStatus(@RequestParam Long loggedInEmployeeId,@PathVariable Long leaveId,@RequestBody String leaveStatus){
        Leave leave = leaveService.getLeaveById(leaveId);
        leaveService.updateLeaveStatus(loggedInEmployeeId,leaveId,leaveStatus);
    }

}
