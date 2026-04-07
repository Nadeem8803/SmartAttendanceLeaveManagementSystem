package com.manager.attendenceleavemanagementsystem.service;

import com.manager.attendenceleavemanagementsystem.dto.AttendenceRequestDTO;
import com.manager.attendenceleavemanagementsystem.dto.AttendenceResponseDTO;
import com.manager.attendenceleavemanagementsystem.entity.Attendence;

import java.util.List;

public interface AttendenceService {
    public AttendenceResponseDTO markAttendence(AttendenceRequestDTO attendenceRequestDTO);
    public List<Attendence> getAllAttendence();
    public List<Attendence> getAttendenceByEmployeeId(Long employeeId);
    public Attendence checkOut(Long employeeId);
}
