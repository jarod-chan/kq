package cn.fyg.kq.domain.model.modmenu.module;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ModuleRepository extends CrudRepository<Module, String>, JpaSpecificationExecutor<Module> {

}
