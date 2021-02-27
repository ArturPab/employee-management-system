package pl.pabjan.employeemanagementsystem.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.pabjan.employeemanagementsystem.model.employee.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {


    @Query("Select e from Employee e left join fetch e.contract left join fetch e.address")
    List<Employee> findAllEmployees(Pageable page);

    @Query("Select e from Employee e left join fetch e.contract left join fetch e.address where e.employeeId=?1")
    Optional<Employee> findEmployeeById(Long id);

    @Query("Select e from Employee e left join fetch e.contract left join fetch e.address where e.name=?1")
    List<Employee> findEmployeeByName(String name);

    @Query("Select e from Employee e left join fetch e.contract left join fetch e.address where e.lastName=?1")
    List<Employee> findEmployeeByLastName(String lastName);

    @Query("Select e from Employee e left join fetch e.contract left join fetch e.address where e.address.city=?1")
    List<Employee> findEmployeeByCity(String city);
}
