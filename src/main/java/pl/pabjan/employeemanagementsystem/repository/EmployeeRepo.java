package pl.pabjan.employeemanagementsystem.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.pabjan.employeemanagementsystem.model.Employee;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {


    @Query("Select e from Employee e left join fetch e.contract left join fetch e.address")
    List<Employee> findAllEmployees(Pageable page);
}
