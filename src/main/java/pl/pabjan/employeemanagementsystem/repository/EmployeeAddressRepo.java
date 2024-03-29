package pl.pabjan.employeemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pabjan.employeemanagementsystem.model.employee.EmployeeAddress;

import java.util.List;

@Repository
public interface EmployeeAddressRepo extends JpaRepository<EmployeeAddress, Long> {
    List<EmployeeAddress> findAllByEmployeeIdIn(List<Long> ids);
}
