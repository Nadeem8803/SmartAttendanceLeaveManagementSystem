package com.manager.attendenceleavemanagementsystem.service;

import com.manager.attendenceleavemanagementsystem.dto.LeaveRequestDTO;
import com.manager.attendenceleavemanagementsystem.dto.LeaveResponseDTO;
import com.manager.attendenceleavemanagementsystem.entity.Employee;
import com.manager.attendenceleavemanagementsystem.entity.Leave;

import java.util.List;

public interface LeaveService {
    public LeaveResponseDTO applyLeave(LeaveRequestDTO leaveRequestDTO);
    public Leave getLeaveById(Long leaveId);
    public List<Leave> getAllLeaves();
    public List<Leave> getLeaveByEmployeeId(Long employeeId);
    public void updateLeaveStatus(Long loggedInEmployeeId, Long leaveId, String leaveStatus);
    public void deleteLeaveById(Long loggedInEmployeeId,Long leaveId);
}
