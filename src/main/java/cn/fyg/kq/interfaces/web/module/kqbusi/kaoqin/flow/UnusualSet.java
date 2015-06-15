package cn.fyg.kq.interfaces.web.module.kqbusi.kaoqin.flow;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;

import cn.fyg.kq.application.KaoqinService;
import cn.fyg.kq.domain.model.kaoqin.busi.Kaoqin;
import cn.fyg.kq.domain.model.kaoqin.busi.KaoqinState;
import cn.fyg.kq.interfaces.web.shared.constant.FlowConstant;

public class UnusualSet  implements JavaDelegate{
	
	private Expression kaoqinServiceExp;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		KaoqinService kaoqinService =(KaoqinService) kaoqinServiceExp.getValue(execution);
		Long businessId = (Long) execution.getVariableLocal(FlowConstant.BUSINESS_ID);
		
		Kaoqin kaoqin = kaoqinService.find(businessId);
		kaoqin.setState(KaoqinState.overdue);
		kaoqinService.save(kaoqin);
		
	}

}
