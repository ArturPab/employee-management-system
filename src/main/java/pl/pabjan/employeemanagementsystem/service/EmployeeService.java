package pl.pabjan.employeemanagementsystem.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.pabjan.employeemanagementsystem.controller.dto.EmployeeRequest;
import pl.pabjan.employeemanagementsystem.controller.dto.EmployeeResponse;
import pl.pabjan.employeemanagementsystem.exceptions.EmployeeManagementSystemException;
import pl.pabjan.employeemanagementsystem.mapper.EmployeeAddressMapper;
import pl.pabjan.employeemanagementsystem.mapper.EmployeeContractMapper;
import pl.pabjan.employeemanagementsystem.mapper.EmployeeMapper;
import pl.pabjan.employeemanagementsystem.mapper.UserMapper;
import pl.pabjan.employeemanagementsystem.model.employee.Employee;
import pl.pabjan.employeemanagementsystem.model.account.User;
import pl.pabjan.employeemanagementsystem.repository.EmployeeAddressRepo;
import pl.pabjan.employeemanagementsystem.repository.EmployeeContractRepo;
import pl.pabjan.employeemanagementsystem.repository.EmployeeRepo;
import pl.pabjan.employeemanagementsystem.repository.UserRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final UserRepo userRepo;
    private final EmployeeRepo employeeRepo;
    private final EmployeeAddressRepo addressRepo;
    private final EmployeeContractRepo contractRepo;
    private final EmployeeMapper employeeMapper;
    private final UserMapper userMapper;
    private final EmployeeAddressMapper addressMapper;
    private final EmployeeContractMapper contractMapper;
    private final EmailServiceImpl emailService;
    private static final int PAGE_SIZE = 5;


    public List<EmployeeResponse> getAllEmployees(Integer page) {

        int pageNumber = (page != null && page>=0 ? page: 0);

        return employeeRepo.findAllEmployees(PageRequest.of(pageNumber, PAGE_SIZE))
                .stream()
                .map(employeeMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public EmployeeResponse getEmployeeById(Long id) {
        return employeeMapper.mapToDto(employeeRepo.findEmployeeById(id).orElseThrow(() -> new EmployeeManagementSystemException("Not found employee with id " + id)));
    }

    public List<EmployeeResponse> getEmployeesByName(String name) {
        return employeeRepo.findEmployeeByName(name)
                .stream()
                .map(employeeMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<EmployeeResponse> getEmployeesByLastName(String lastName) {
        return employeeRepo.findEmployeeByLastName(lastName)
                .stream()
                .map(employeeMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<EmployeeResponse> getEmployeesByCity(String city) {
        return employeeRepo.findEmployeeByCity(city)
                .stream()
                .map(employeeMapper::mapToDto)
                .collect(Collectors.toList());

    }

    public void generatePasswordAndSaveEmployeeAndSendMail(EmployeeRequest employeeRequest) {
        String password = PasswordService.getGeneratedCommonLangPassword();

        User user = userRepo.save(userMapper.map(employeeRequest, password));
        Employee employee = employeeRepo.save(employeeMapper.map(employeeRequest, user.getUserId()));
        addressRepo.save(addressMapper.map(employeeRequest, employee.getEmployeeId()));
        contractRepo.save(contractMapper.map(employeeRequest, employee.getEmployeeId()));

        emailService.sendmail(employeeRequest.getEmail(),
                getSubject(),
                getContent(employeeRequest, password));
    }

    private String getSubject() {
        return "Password";
    }

    private String getContent(EmployeeRequest employeeRequest, String password) {
        return "Your login: " + employeeRequest.getEmail() + "\n" + "Password: " + password;
    }
}
