package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import project.model.Accounts;
import project.model.Category;

public interface AccountRepository extends JpaRepository<Accounts, Integer>, JpaSpecificationExecutor<Accounts> {
}
