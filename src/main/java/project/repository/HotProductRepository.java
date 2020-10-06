package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import project.model.HotProducts;

public interface HotProductRepository extends JpaRepository<HotProducts, Integer>, JpaSpecificationExecutor<HotProducts> {
    HotProducts findAllByHotProductId(int id);
}
