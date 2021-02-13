package pl.pabjan.employeemanagementsystem.mapper;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.pabjan.employeemanagementsystem.controller.dto.EmployeeRequest;
import pl.pabjan.employeemanagementsystem.model.User;
import pl.pabjan.employeemanagementsystem.service.PasswordService;

import java.time.Instant;

@Component
@AllArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public User map(EmployeeRequest employeeRequest, String password) {
        User user = new User();
        user.setEmail(employeeRequest.getEmail());
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(password));
        user.setCreated(Instant.now());

        return user;
    }
}
