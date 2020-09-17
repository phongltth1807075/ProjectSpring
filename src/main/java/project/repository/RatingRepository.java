package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import project.model.Accounts;
import project.model.Rating;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer>, JpaSpecificationExecutor<Rating> {
    List<Rating> findAllByProductId(int id);

    Rating findById(int id);
}
