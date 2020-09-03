package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import project.model.Roles;

public interface RoleRepository extends JpaRepository<Roles, Integer>, JpaSpecificationExecutor<Roles> {
}
