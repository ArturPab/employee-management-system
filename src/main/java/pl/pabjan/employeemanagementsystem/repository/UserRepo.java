package pl.pabjan.employeemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pabjan.employeemanagementsystem.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
}
