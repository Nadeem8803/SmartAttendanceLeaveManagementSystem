package com.manager.attendenceleavemanagementsystem.util;

import com.manager.attendenceleavemanagementsystem.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class AuthrizationUtil {

    public void requireAdmin(Employee employee){
        if(!"ADMIN".equals(employee.getEmpAuthenticationRole())){
            throw new RuntimeException("only Admin is allowed.");
        }
    }

    public void requireEmployee(Employee employee){
        if(!"EMPLOYEE".equals(employee.getEmpAuthenticationRole())){
            throw new RuntimeException("only Employee is allowed.");
        }
    }

}
