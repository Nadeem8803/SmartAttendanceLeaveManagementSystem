package com.manager.attendenceleavemanagementsystem.repository;

import com.manager.attendenceleavemanagementsystem.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave,Long> {
    public List<Leave> findByEmployeeEmpId(Long employeeId);
}
