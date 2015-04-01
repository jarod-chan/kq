package cn.fyg.kq.interfaces.web.module.hr.leave.flow;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;

import cn.fyg.kq.application.LeaveService;
import cn.fyg.kq.domain.model.vacation.common.BusiState;
import cn.fyg.kq.domain.model.vacation.leave.Leave;
import cn.fyg.kq.interfaces.web.shared.tool.FlowConstant;

public class EndSet implements JavaDelegate {
	
	private Expression leaveServiceExp;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		LeaveService leaveService =(LeaveService) leaveServiceExp.getValue(execution);
		Long businessId = (Long) execution.getVariableLocal(FlowConstant.BUSINESS_ID);
		Leave leave = leaveService.find(businessId);
		leave.setBusiState(BusiState.finish);
		leaveService.save(leave);
	}

}
