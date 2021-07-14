package restfull.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import restfull.entity.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findById(Long id);

    boolean existsByPhone(String phone);

    Users findByName(String name);
}
