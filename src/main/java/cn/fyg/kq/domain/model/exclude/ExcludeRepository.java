package cn.fyg.kq.domain.model.exclude;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ExcludeRepository extends CrudRepository< Exclude, Long>, JpaSpecificationExecutor< Exclude> {
	


}
