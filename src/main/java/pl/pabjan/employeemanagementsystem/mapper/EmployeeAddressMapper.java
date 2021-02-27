package pl.pabjan.employeemanagementsystem.mapper;

import org.springframework.stereotype.Component;
import pl.pabjan.employeemanagementsystem.controller.dto.EmployeeRequest;
import pl.pabjan.employeemanagementsystem.model.employee.EmployeeAddress;

@Component
public class EmployeeAddressMapper {

    public EmployeeAddress map(EmployeeRequest employeeRequest, Long employeeId) {
        EmployeeAddress address = new EmployeeAddress();
        address.setEmployeeId(employeeId);
        address.setCity(employeeRequest.getCity());
        address.setStreet(employeeRequest.getStreet());
        address.setHouseNumber(employeeRequest.getHouseNumber());
        address.setZipCode(employeeRequest.getZipCode());

        return address;
    }
}
