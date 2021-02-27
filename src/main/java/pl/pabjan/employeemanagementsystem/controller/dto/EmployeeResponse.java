package pl.pabjan.employeemanagementsystem.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {

    private Long employeeId;

    private String name;

    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    private String street;

    private String houseNumber;

    private String city;

    private String zipCode;

    private String jobPosition;

    private String contractType;

    private Integer salaryInPLN;
}
