package pl.pabjan.employeemanagementsystem.mapper;

import org.springframework.stereotype.Component;
import pl.pabjan.employeemanagementsystem.controller.dto.EmployeeRequest;
import pl.pabjan.employeemanagementsystem.controller.dto.EmployeeResponse;
import pl.pabjan.employeemanagementsystem.model.employee.Employee;
import pl.pabjan.employeemanagementsystem.model.employee.EmployeeAddress;
import pl.pabjan.employeemanagementsystem.model.employee.EmployeeContract;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

    public EmployeeResponse mapToDto(Employee employee) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setEmployeeId(employee.getEmployeeId());
        employeeResponse.setBirthdate(employee.getBirthdate());
        employeeResponse.setName(employee.getName());
        employeeResponse.setLastName(employee.getLastName());
        employeeResponse.setStreet(employee.getAddress().getStreet());
        employeeResponse.setHouseNumber(employee.getAddress().getHouseNumber());
        employeeResponse.setCity(employee.getAddress().getCity());
        employeeResponse.setZipCode(employee.getAddress().getZipCode());
        employeeResponse.setContractType(employee.getContract().getContractType());
        employeeResponse.setJobPosition(employee.getContract().getJobPosition());
        employeeResponse.setSalaryInPLN(employee.getContract().getSalaryInPLN());

        return employeeResponse;
    }

    public List<EmployeeResponse> mapToDtos(List<Employee> employees, List<EmployeeAddress> addresses, List<EmployeeContract> contracts) {
        employees.forEach(employee -> employee.setAddress(extractAddress(employee.getEmployeeId(), addresses)));
        employees.forEach(employee -> employee.setContract(extractContract(employee.getEmployeeId(), contracts)));

        return employees
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private EmployeeContract extractContract(Long employeeId, List<EmployeeContract> contracts) {
        for (EmployeeContract contract : contracts) {
            if (contract.getEmployeeId().equals(employeeId))
                return contract;
        }
        return null;
    }

    private EmployeeAddress extractAddress(Long employeeId, List<EmployeeAddress> addresses) {
        for (EmployeeAddress address : addresses) {
            if (address.getEmployeeId().equals(employeeId))
                return address;
        }
        return null;
    }

    public Employee map(EmployeeRequest employeeRequest, Long userId) {
        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setBirthdate(employeeRequest.getBirthdate());
        employee.setUserId(userId);

        return employee;
    }
}
