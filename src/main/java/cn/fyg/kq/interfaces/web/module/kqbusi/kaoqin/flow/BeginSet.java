package cn.fyg.kq.interfaces.web.module.kqbusi.kaoqin.flow;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;

import cn.fyg.kq.application.KaoqinService;
import cn.fyg.kq.application.OpinionService;
import cn.fyg.kq.domain.model.kaoqin.busi.Kaoqin;
import cn.fyg.kq.domain.model.kaoqin.busi.KaoqinState;
import cn.fyg.kq.interfaces.web.shared.constant.FlowConstant;

public class BeginSet implements JavaDelegate {
	
	private Expression kaoqinServiceExp;
	
	private Expression opinionServiceExp;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		KaoqinService kaoqinService =(KaoqinService) kaoqinServiceExp.getValue(execution);
		OpinionService opinionService=(OpinionService) opinionServiceExp.getValue(execution);
		Long businessId = (Long) execution.getVariableLocal(FlowConstant.BUSINESS_ID);
		
		Kaoqin kaoqin = kaoqinService.find(businessId);
		kaoqin.setState(KaoqinState.process);
		kaoqinService.save(kaoqin);
		
		opinionService.clear(Kaoqin.BUSI_CODE, businessId);
		
		//TODO 临时处理流程汇报人员的变量
		Map<String,Object> context = new HashMap<String,Object>();
		context=execution.getVariables();
		execution.setVariableLocal("context", context);
	}

}
