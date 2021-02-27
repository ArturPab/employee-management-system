package pl.pabjan.employeemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.pabjan.employeemanagementsystem.model.account.User;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    @Query("select u from User u left join fetch u.employee where u.email=?1")
    Optional<User> findByEmail(String email);
}
