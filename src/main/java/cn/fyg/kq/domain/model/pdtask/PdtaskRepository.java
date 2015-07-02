package cn.fyg.kq.domain.model.pdtask;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;


public interface PdtaskRepository extends CrudRepository<Pdtask, Long>, JpaSpecificationExecutor<Pdtask> {


}
