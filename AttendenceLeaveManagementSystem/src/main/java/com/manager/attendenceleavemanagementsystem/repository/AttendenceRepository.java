package com.manager.attendenceleavemanagementsystem.repository;

import com.manager.attendenceleavemanagementsystem.entity.Attendence;
import com.manager.attendenceleavemanagementsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendenceRepository extends JpaRepository<Attendence,Long> {
    public List<Attendence> findByEmployeeEmpId(Long employeeId);
    public Optional<Attendence> findByEmployeeEmpIdAndAttendenceDate(Long employeeId, LocalDate attendenceDate);
}
