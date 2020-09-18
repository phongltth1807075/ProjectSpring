package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import project.model.CommentRating;

import java.util.List;


public interface CommentRatingRepository extends JpaRepository<CommentRating, Integer>, JpaSpecificationExecutor<CommentRating> {

    List<CommentRating> findAllByProductId(int id);

    CommentRating findById(int id);

}
