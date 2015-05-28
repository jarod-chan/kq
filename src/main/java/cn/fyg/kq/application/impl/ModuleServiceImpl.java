package cn.fyg.kq.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import cn.fyg.kq.application.ModuleService;
import cn.fyg.kq.application.common.impl.SericeQueryImpl;
import cn.fyg.kq.domain.model.modmenu.module.Module;
import cn.fyg.kq.domain.model.modmenu.module.ModuleRepository;

@Service
public class ModuleServiceImpl extends SericeQueryImpl<Module> implements ModuleService {

	@Autowired
	ModuleRepository moduleRepository;
	
	@Override
	public JpaSpecificationExecutor<Module> getSpecExecutor() {
		return this.moduleRepository;
	}


}
