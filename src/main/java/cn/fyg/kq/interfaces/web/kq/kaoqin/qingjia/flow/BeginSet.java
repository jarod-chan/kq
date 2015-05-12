package cn.fyg.kq.interfaces.web.kq.kaoqin.qingjia.flow;

import java.math.BigDecimal;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class BeginSet implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		execution.setVariableLocal("isAggree", Boolean.TRUE);
		execution.setVariableLocal("acturlDay", new BigDecimal("7.0"));

	}

}
