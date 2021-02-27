package pl.pabjan.employeemanagementsystem.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class EmployeeRequest {

    @NotBlank(message = "Field can't be empty or null!")
    @Email
    private String email;

    @NotBlank(message = "Field can't be empty or null!")
    @Size(min = 2, max = 30, message = "Field have to be between 2 and 30 characters")
    private String name;

    @NotBlank(message = "Field can't be empty or null!")
    @Size(min = 2, max = 30, message = "Field have to be between 2 and 30 characters")
    private String lastName;

    @NotBlank(message = "Field can't be empty or null!")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    @NotBlank(message = "Field can't be empty or null!")
    @Size(min = 2, max = 30, message = "Field have to be between 2 and 30 characters")
    private String street;

    @NotBlank(message = "Field can't be empty or null!")
    @Size(min = 1, max = 8)
    private String houseNumber;

    @NotBlank(message = "Field can't be empty or null!")
    @Size(min = 2, max = 30, message = "Field have to be between 2 and 30 characters")
    private String city;

    @NotBlank(message = "Field can't be empty or null!")
    @Pattern(regexp = "^[0-9]{2}(?:-[0-9]{3})?$", message = "Zip-code have to be in 00-000 format")
    private String zipCode;

    @NotBlank(message = "Field can't be empty or null!")
    @Size(min = 2, max = 30)
    private String jobPosition;

    @NotBlank(message = "Field can't be empty or null!")
    @Size(min = 2, max = 30, message = "Field have to be between 2 and 30 characters")
    private String contractType;

    @NotBlank(message = "Field can't be empty or null!")
    private Integer salaryInPLN;
}
