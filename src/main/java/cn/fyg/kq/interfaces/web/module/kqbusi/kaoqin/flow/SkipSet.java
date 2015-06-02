package cn.fyg.kq.interfaces.web.module.kqbusi.kaoqin.flow;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class SkipSet implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		execution.setVariableLocal(KaoqinVarname.IS_AGGREE, Boolean.TRUE);
	}

}
