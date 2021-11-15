package pl.pabjan.employeemanagementsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.pabjan.employeemanagementsystem.controller.dto.EmployeeResponse;
import pl.pabjan.employeemanagementsystem.model.account.User;
import pl.pabjan.employeemanagementsystem.model.employee.Employee;
import pl.pabjan.employeemanagementsystem.model.employee.EmployeeAddress;
import pl.pabjan.employeemanagementsystem.model.employee.EmployeeContract;
import pl.pabjan.employeemanagementsystem.repository.EmployeeAddressRepo;
import pl.pabjan.employeemanagementsystem.repository.EmployeeContractRepo;
import pl.pabjan.employeemanagementsystem.repository.EmployeeRepo;
import pl.pabjan.employeemanagementsystem.repository.UserRepo;


import javax.transaction.Transactional;

import java.time.Instant;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin@admin.com", password = "test")
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private EmployeeContractRepo contractRepo;

    @Autowired
    private EmployeeAddressRepo addressRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetEmployee() throws Exception {

        //given
        User newUser = getNewUser();

        Employee newEmployee = getNewEmployee(newUser);

        EmployeeAddress newAddress = getNewAddress(newEmployee);

        EmployeeContract newContract = getNewContract(newEmployee);

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/by-id/" + newEmployee.getEmployeeId()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();

        //then
        EmployeeResponse employee = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), EmployeeResponse.class);
        assertThat(employee).isNotNull();
        assertThat(employee.getEmployeeId()).isEqualTo(newEmployee.getEmployeeId());

        deleteData(newUser, newEmployee, newAddress, newContract);
    }

    @Test
    @Transactional
    void shouldSaveNewEmployee() throws Exception {

        //when
        MvcResult employeeRequest = mockMvc.perform(MockMvcRequestBuilders.post("/api/employees")
        .content("{\"email\": \"jan.kowalski@test.com\", " +
                "\"name\": \"Jan\", " +
                "\"lastName\": \"Kowalski\", " +
                "\"birthdate\": \"2000-09-05\", " +
                "\"street\": \"Bielska 100\", " +
                "\"houseNumber\": \"100\", " +
                "\"city\": \"Tychy\", " +
                "\"zipCode\": \"43-100\", " +
                "\"jobPosition\": \"Tester\", " +
                "\"contractType\": \"B2B\", " +
                "\"salaryInPLN\": 7000}"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
    }

    private EmployeeContract getNewContract(Employee newEmployee) {
        EmployeeContract newContract = new EmployeeContract();
        newContract.setContractType("B2B");
        newContract.setJobPosition("Tester");
        newContract.setSalaryInPLN(7000);
        newContract.setEmployeeId(newEmployee.getEmployeeId());
        contractRepo.save(newContract);
        return newContract;
    }

    private EmployeeAddress getNewAddress(Employee newEmployee) {
        EmployeeAddress newAddress = new EmployeeAddress();
        newAddress.setCity("Tychy");
        newAddress.setStreet("ul. Bielska");
        newAddress.setHouseNumber("100");
        newAddress.setZipCode("43-100");
        newAddress.setEmployeeId(newEmployee.getEmployeeId());
        addressRepo.save(newAddress);
        return newAddress;
    }

    private Employee getNewEmployee(User newUser) {
        Employee newEmployee = new Employee();
        newEmployee.setBirthdate(LocalDate.of(2000, 5, 9));
        newEmployee.setName("Jan");
        newEmployee.setLastName("Kowalski");
        newEmployee.setUserId(newUser.getUserId());
        employeeRepo.save(newEmployee);
        return newEmployee;
    }

    private User getNewUser() {
        User newUser = new User();
        newUser.setCreated(Instant.now());
        newUser.setEmail("testemployee@test.com");
        newUser.setEnabled(true);
        newUser.setPassword("test");
        newUser.setRole("USER");
        userRepo.save(newUser);
        return newUser;
    }

    private void deleteData(User newUser, Employee newEmployee, EmployeeAddress newAddress, EmployeeContract newContract) {
        contractRepo.delete(newContract);
        addressRepo.delete(newAddress);
        employeeRepo.delete(newEmployee);
        userRepo.delete(newUser);
    }
}