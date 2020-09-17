package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import project.model.Transporters;


public interface TransportersRepository extends JpaRepository<Transporters, Integer>, JpaSpecificationExecutor<Transporters> {

}
