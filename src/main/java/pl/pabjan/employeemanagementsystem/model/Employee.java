package pl.pabjan.employeemanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birthdate")
    @JsonFormat(pattern = "dddd-MM-dd")
    private LocalDate birthdate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "employee_id", updatable = false, insertable = false)
    private EmployeeAddress address;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "employee_id", updatable = false, insertable = false)
    private EmployeeContract contract;
}
