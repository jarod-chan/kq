package cn.fyg.kq.interfaces.web.module.hr.back.flow;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;

import cn.fyg.kq.application.BackService;
import cn.fyg.kq.application.OpinionService;
import cn.fyg.kq.domain.model.vacation.back.Back;
import cn.fyg.kq.interfaces.web.module.hr.CommonVarName;
import cn.fyg.kq.interfaces.web.module.hr.back.BackVarName;
import cn.fyg.kq.interfaces.web.shared.tool.FlowConstant;

public class BeginSet implements JavaDelegate {
	
	private Expression backServiceExp;
	
	private Expression opinionServiceExp;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		BackService backService =(BackService) backServiceExp.getValue(execution);
		OpinionService opinionService=(OpinionService) opinionServiceExp.getValue(execution);
		Long businessId = (Long) execution.getVariableLocal(FlowConstant.BUSINESS_ID);
		Back back = backService.find(businessId);
	
		opinionService.clear(Back.BUSINESS_CODE, businessId);
		execution.setVariableLocal(BackVarName.ACTURL_DAY, back.getActurlDay());
		execution.setVariableLocal(BackVarName.IS_NORMAL_BACK, back.isNormalBack());
		//把单据号作为一个流程变量，在发送邮件时方便取值
		execution.setVariable(CommonVarName.BUSINESS_NO, back.getNo());
	}

}
