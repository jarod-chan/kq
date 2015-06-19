package cn.fyg.kq.domain.model.noti;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface NotiRepository extends CrudRepository<Noti, Long>, JpaSpecificationExecutor<Noti>{

}
