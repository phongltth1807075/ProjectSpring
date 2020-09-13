package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import project.model.OrderDetailEntity;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Integer>, JpaSpecificationExecutor<OrderDetailEntity> {
    List<OrderDetailEntity> findAllByOrderId(int id);
}
