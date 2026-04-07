package com.manager.attendenceleavemanagementsystem.serviceimpl;

import com.manager.attendenceleavemanagementsystem.dto.LeaveRequestDTO;
import com.manager.attendenceleavemanagementsystem.dto.LeaveResponseDTO;
import com.manager.attendenceleavemanagementsystem.entity.Employee;
import com.manager.attendenceleavemanagementsystem.entity.Leave;
import com.manager.attendenceleavemanagementsystem.repository.EmployeeRepository;
import com.manager.attendenceleavemanagementsystem.repository.LeaveRepository;
import com.manager.attendenceleavemanagementsystem.service.LeaveService;
import com.manager.attendenceleavemanagementsystem.util.AuthrizationUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository;
    private final AuthrizationUtil authrizationUtil;

    LeaveServiceImpl(LeaveRepository leaveRepository, EmployeeRepository employeeRepository, AuthrizationUtil authrizationUtil){
        this.leaveRepository = leaveRepository;
        this.employeeRepository = employeeRepository;
        this.authrizationUtil = authrizationUtil;
    }

    @Override
    public LeaveResponseDTO applyLeave(LeaveRequestDTO leaveRequestDTO) {

        Employee employee = employeeRepository
                .findById(leaveRequestDTO.getEmployeeId())
                .orElseThrow(()-> new RuntimeException("employee not found "));


        Leave leave =  new Leave();
        leave.setEmployee(employee);
        leave.setLeaveType(leaveRequestDTO.getLeaveType());
        leave.setStartDate(leaveRequestDTO.getStartDate());
        leave.setEndDate(leaveRequestDTO.getEndDate());
        leave.setLeaveReason(leaveRequestDTO.getLeaveReason());
        long totalDays = ChronoUnit.DAYS.between(leaveRequestDTO.getStartDate(),leaveRequestDTO.getEndDate()) + 1;
        leave.setTotalDays((int) totalDays );

        Leave saved = leaveRepository.save(leave);

        LeaveResponseDTO leaveResponseDTO = new LeaveResponseDTO();

        leaveResponseDTO.setLeaveType(leave.getLeaveType());
        leaveResponseDTO.setLeaveId(leave.getLeaveId());
        leaveResponseDTO.setStartDate(leave.getStartDate());
        leaveResponseDTO.setEndDate(leave.getEndDate());
        leaveResponseDTO.setTotalDays(leave.getTotalDays());
        leaveResponseDTO.setAppliedDate(leave.getAppliedDate());
        leaveResponseDTO.setLeaveStatus(leave.getLeaveStatus());
        leaveResponseDTO.setLeaveReason(leave.getLeaveReason());

        return leaveResponseDTO;
    }

    @Override
    public Leave getLeaveById(Long leaveId) {
        return leaveRepository.findById(leaveId).orElseThrow(()->new RuntimeException("no leave find with id : "+leaveId));
    }

    @Override
    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }

    @Override
    public List<Leave> getLeaveByEmployeeId(Long employeeId) {
        return leaveRepository.findByEmployeeEmpId(employeeId);
    }

    @Override
    public void updateLeaveStatus(Long loggedInEmployeeId,Long leaveId, String leaveStatus) {
        Employee employee = employeeRepository
                .findById(loggedInEmployeeId)
                .orElseThrow(()->new RuntimeException("emlpoyee not found."));

        authrizationUtil.requireAdmin(employee);

        Leave leave = leaveRepository
                .findById(leaveId)
                .orElseThrow(()->new RuntimeException("no leave find with is : "+ leaveId));

        leave.setLeaveStatus(leaveStatus);
        leaveRepository.save(leave);
    }

    @Override
    public void deleteLeaveById(Long loggedInEmployeeId, Long leaveId) {

        Employee employee = employeeRepository
                .findById(loggedInEmployeeId)
                .orElseThrow(()-> new RuntimeException("employee not found"));

        authrizationUtil.requireAdmin(employee);

        Leave leave = leaveRepository.findById(leaveId).orElseThrow(()->new RuntimeException("no leave find with is : "+ leaveId));
        leaveRepository.deleteById(leaveId);
    }
}
