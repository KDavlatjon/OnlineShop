package restfull.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import restfull.entity.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findById(Long id);
}
