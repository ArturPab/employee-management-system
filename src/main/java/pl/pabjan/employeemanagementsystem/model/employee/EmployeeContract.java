package pl.pabjan.employeemanagementsystem.model.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee_contract")
public class EmployeeContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_contract_id")
    private Long employeeContractId;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "job_position")
    private String jobPosition;

    @Column(name = "contract_type")
    private String contractType;

    @Column(name = "salary_in_pln")
    private Integer salaryInPLN;

}
