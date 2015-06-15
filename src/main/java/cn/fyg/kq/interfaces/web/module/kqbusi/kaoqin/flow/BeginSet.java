package cn.fyg.kq.interfaces.web.module.kqbusi.kaoqin.flow;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;

import cn.fyg.kq.application.KaoqinService;
import cn.fyg.kq.application.OpinionService;
import cn.fyg.kq.application.ReptService.StatEnum;
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

		
		Long businessId = (Long) execution.getVariable(FlowConstant.BUSINESS_ID);
		Kaoqin kaoqin = kaoqinService.find(businessId);
		kaoqin.setState(KaoqinState.process);
		kaoqinService.save(kaoqin);
		
		opinionService.clear(Kaoqin.BUSI_CODE, businessId);
		
		//已经执行的节点
		execution.setVariable("done_node", null);
		//需要达到的审批等级
		StatEnum require_node = StatEnum.rangOf(kaoqin.getItem_all());
		execution.setVariable("require_node",require_node);
		
	}

}
