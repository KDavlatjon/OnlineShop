package restfull.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import restfull.entity.Orders;

import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    Optional<Orders> findById(Long id);
}
