package cn.fyg.kq.interfaces.web.module.hr.back.flow;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;

import cn.fyg.kq.application.BackService;
import cn.fyg.kq.application.CompdateService;
import cn.fyg.kq.application.LeaveService;
import cn.fyg.kq.domain.model.vacation.back.Back;
import cn.fyg.kq.domain.model.vacation.common.BusiState;
import cn.fyg.kq.domain.model.vacation.compdate.DayResult;
import cn.fyg.kq.domain.model.vacation.leave.Leave;
import cn.fyg.kq.interfaces.web.module.hr.back.BackVarName;
import cn.fyg.kq.interfaces.web.shared.tool.FlowConstant;

public class ProduceBack implements JavaDelegate {
	
	private Expression leaveServiceExp;
	
	private Expression backServiceExp;
	
	private Expression compdateServiceExp;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		LeaveService leaveService =(LeaveService) leaveServiceExp.getValue(execution);
		BackService backService =(BackService) backServiceExp.getValue(execution);
		CompdateService compdateService=(CompdateService) compdateServiceExp.getValue(execution);
		
		Long leaveId=(Long)execution.getVariable(BackVarName.LEAVE_ID);
		Leave leave = leaveService.find(leaveId);
		Back back=backService.create(leave.getUser());
		back.setLeave(leave);
		back.setBegDayitem(leave.getBegDayitem());
		back.setEndDayitem(leave.getEndDayitem());
		back.setBusiState(BusiState.execute);
		back.setLeaveType(leave.getLeaveType());
		DayResult dayResult = compdateService.computerDay(back.getBegDayitem(), back.getEndDayitem());
		back.setNatureDay(dayResult.natureDay());
		back.setActurlDay(dayResult.acturlDay());
		
		backService.save(back);
		
		execution.setVariable(FlowConstant.BUSINESS_ID, back.getId());
	}

}
