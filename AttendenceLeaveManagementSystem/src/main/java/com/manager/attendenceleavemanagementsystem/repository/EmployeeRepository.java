package com.manager.attendenceleavemanagementsystem.repository;

import com.manager.attendenceleavemanagementsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public List<Employee> findByActiveTrue();
    public List<Employee> findByActiveFalse();
    public Optional<Employee> findByEmpEmail(String empEmail);
}
