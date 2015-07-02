package cn.fyg.kq.domain.model.pdtask;

import java.util.Date;

public class PdtaskFactory {
	
	public static Pdtask create(Long period_id,String taskname){
		Pdtask pdtask = new Pdtask();
		pdtask.setPeriod_id(period_id);
		pdtask.setTaskname(taskname);
		pdtask.setState(TaskState.append);
		pdtask.setCreatetime(new Date());
		return pdtask;
	}

}
