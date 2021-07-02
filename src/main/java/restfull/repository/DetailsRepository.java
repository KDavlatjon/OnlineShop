package restfull.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import restfull.entity.Details;

import java.util.List;

@Repository
public interface DetailsRepository extends JpaRepository<Details, Long> {

    @Query(value = "select * from details d where d.order_id = :orderId", nativeQuery = true)
    List<Details> findDetailsListByOrderId(@Param("orderId") Long orderId);
//    List<Details> findByOrderId(Long orderId);
}
