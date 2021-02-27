package pl.pabjan.employeemanagementsystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pabjan.employeemanagementsystem.controller.dto.EmployeeRequest;
import pl.pabjan.employeemanagementsystem.controller.dto.EmployeeResponse;
import pl.pabjan.employeemanagementsystem.service.EmployeeService;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees(@RequestParam(required = false) Integer page) {
        return status(HttpStatus.OK).body(employeeService.getAllEmployees(page));
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
        return status(HttpStatus.OK).body(employeeService.getEmployeeById(id));
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<List<EmployeeResponse>> getEmployeeByName(@PathVariable String name) {
        return status(HttpStatus.OK).body(employeeService.getEmployeesByName(name));
    }

    @GetMapping("/by-last-name/{lastName}")
    public ResponseEntity<List<EmployeeResponse>> getEmployeeByLastName(@PathVariable String lastName) {
        return status(HttpStatus.OK).body(employeeService.getEmployeesByLastName(lastName));
    }

    @GetMapping("/by-city/{city}")
    public ResponseEntity<List<EmployeeResponse>> getEmployeeByCity(@PathVariable String city) {
        return status(HttpStatus.OK).body(employeeService.getEmployeesByCity(city));
    }

    @PostMapping
    public ResponseEntity<Void> saveEmployee(@RequestBody EmployeeRequest employeeRequest) {
        employeeService.generatePasswordAndSaveEmployeeAndSendMail(employeeRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
