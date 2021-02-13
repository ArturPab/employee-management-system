package pl.pabjan.employeemanagementsystem.mapper;

import org.springframework.stereotype.Component;
import pl.pabjan.employeemanagementsystem.controller.dto.EmployeeRequest;
import pl.pabjan.employeemanagementsystem.model.EmployeeContract;

@Component
public class EmployeeContractMapper {

    public EmployeeContract map(EmployeeRequest employeeRequest, Long employeeId) {

        EmployeeContract contract = new EmployeeContract();
        contract.setContractType(employeeRequest.getContractType());
        contract.setJobPosition(employeeRequest.getJobPosition());
        contract.setSalaryInPLN(employeeRequest.getSalaryInPLN());
        contract.setEmployeeId(employeeId);

        return contract;
    }
}
