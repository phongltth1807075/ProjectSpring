package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import project.model.Accounts;
import project.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer>, JpaSpecificationExecutor<Comment> {
    List<Comment> findAllByProductId(int id);

    Comment findById(int id);

    List<Comment> findAllByAccountId(int id);
}
