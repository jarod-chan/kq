package cn.fyg.kq.interfaces.web.module.kqbusi.kaoqin.flow;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import cn.fyg.kq.interfaces.web.shared.tool.FlowConstant;

public class BeginSet implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
/*		execution.setVariableLocal("isAggree", Boolean.TRUE);
		execution.setVariableLocal("item_all", 4);
		execution.setVariableLocal("businessId", 5);*/
		
		
		Map<String,Object> context = new HashMap<String,Object>();
		
		context=execution.getVariables();
		
		execution.setVariableLocal("context", context);
	}

}
