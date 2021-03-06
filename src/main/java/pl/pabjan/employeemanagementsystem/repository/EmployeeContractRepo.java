package pl.pabjan.employeemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pabjan.employeemanagementsystem.model.EmployeeContract;

@Repository
public interface EmployeeContractRepo extends JpaRepository<EmployeeContract, Long> {
}
