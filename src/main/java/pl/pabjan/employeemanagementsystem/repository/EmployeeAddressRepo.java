package pl.pabjan.employeemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pabjan.employeemanagementsystem.model.EmployeeAddress;

@Repository
public interface EmployeeAddressRepo extends JpaRepository<EmployeeAddress, Long> {
}
