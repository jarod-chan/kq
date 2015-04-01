package cn.fyg.kq.interfaces.web.module.hr.leave.flow;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.Expression;

import cn.fyg.kq.interfaces.web.module.hr.back.BackVarName;
import cn.fyg.kq.interfaces.web.shared.tool.FlowConstant;

public class EndListener implements ExecutionListener {
	private Expression runtimeServiceExp;
	
	@Override
	public void notify(DelegateExecution execution) throws Exception {
		RuntimeService runtimeService = (RuntimeService) runtimeServiceExp.getValue(execution);
		Long businessId = (Long) execution.getVariableLocal(FlowConstant.BUSINESS_ID);
		Map<String, Object> map=new HashMap<String,Object>();
		map.put(BackVarName.LEAVE_ID, businessId);
		runtimeService.startProcessInstanceByMessage("triggerBackMessage",map);
	}

}
