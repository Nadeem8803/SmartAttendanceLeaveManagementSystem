package com.manager.attendenceleavemanagementsystem.serviceimpl;

import com.manager.attendenceleavemanagementsystem.dto.AttendenceRequestDTO;
import com.manager.attendenceleavemanagementsystem.dto.AttendenceResponseDTO;
import com.manager.attendenceleavemanagementsystem.entity.Attendence;
import com.manager.attendenceleavemanagementsystem.entity.Employee;
import com.manager.attendenceleavemanagementsystem.repository.AttendenceRepository;
import com.manager.attendenceleavemanagementsystem.repository.EmployeeRepository;
import com.manager.attendenceleavemanagementsystem.service.AttendenceService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AttendenceServiceImpl implements AttendenceService {

    private final AttendenceRepository attendenceRepository;
    private final EmployeeRepository employeeRepository;

    AttendenceServiceImpl(AttendenceRepository attendenceRepository, EmployeeRepository employeeRepository){
        this.attendenceRepository = attendenceRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public AttendenceResponseDTO markAttendence(AttendenceRequestDTO attendenceRequestDTO) {

        LocalDate today = LocalDate.now();
        Long empId = attendenceRequestDTO.getEmployeeId();


        Employee employee = employeeRepository
                .findById(attendenceRequestDTO.getEmployeeId())
                .orElseThrow(()->new RuntimeException("employee not found"));

        Optional<Attendence> existing = attendenceRepository
                .findByEmployeeEmpIdAndAttendenceDate(empId,today);

        if (existing.isPresent()){
            throw new RuntimeException("attendence already marked.");
        }

        Attendence attendence = new Attendence();
        attendence.setAttendenceStatus("PRESENT");
        attendence.setEmployee(employee);

        Attendence savedAttendence = attendenceRepository.save(attendence);

        AttendenceResponseDTO attendenceResponseDTO = new AttendenceResponseDTO();

        attendenceResponseDTO.setAttendenceId(savedAttendence.getAttendenceId());
        attendenceResponseDTO.setAttendenceDate(savedAttendence.getAttendenceDate());
        attendenceResponseDTO.setAttendenceStatus(savedAttendence.getAttendenceStatus());
        attendenceResponseDTO.setCheckInTime(savedAttendence.getCheckInTime());
        attendenceResponseDTO.setCheckOutTime(savedAttendence.getCheckOutTime());

        return attendenceResponseDTO;
    }

    @Override
    public List<Attendence> getAllAttendence() {
        return attendenceRepository.findAll();
    }

    @Override
    public List<Attendence> getAttendenceByEmployeeId(Long employeeId) {
        return attendenceRepository.findByEmployeeEmpId(employeeId);
    }

    @Override
    public Attendence checkOut(Long employeeId) {

        LocalDate today = LocalDate.now();

        Attendence attendence = attendenceRepository
                .findByEmployeeEmpIdAndAttendenceDate(employeeId,today)
                .orElseThrow(()->new RuntimeException("attendence is not found with id : " + employeeId));

        attendence.setCheckOutTime(LocalTime.now());

        return attendenceRepository.save(attendence);
    }
}
