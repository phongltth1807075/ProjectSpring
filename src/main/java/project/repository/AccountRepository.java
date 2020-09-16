package project.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import project.model.Accounts;

public interface AccountRepository extends JpaRepository<Accounts, Integer>, JpaSpecificationExecutor<Accounts> {

//    Accounts findByEmailAndPassword(String email, String password);

    Accounts findByAccountIdAndStatus(int id, int status);

    Accounts findByStatus(int status);

    Accounts findByToken(String token);

    Accounts findByEmail(String email);

    Accounts findByAccountId(int id);

}
