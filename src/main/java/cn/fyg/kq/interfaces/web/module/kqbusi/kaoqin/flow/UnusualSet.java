package cn.fyg.kq.interfaces.web.module.kqbusi.kaoqin.flow;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;

import cn.fyg.kq.application.KaoqinService;
import cn.fyg.kq.application.NotiService;
import cn.fyg.kq.domain.model.kaoqin.busi.Kaoqin;
import cn.fyg.kq.domain.model.kaoqin.busi.KaoqinItem;
import cn.fyg.kq.domain.model.kaoqin.busi.KaoqinState;
import cn.fyg.kq.domain.model.kaoqin.busi.PassState;
import cn.fyg.kq.interfaces.web.shared.constant.FlowConstant;

public class UnusualSet  implements JavaDelegate{
	
	private Expression kaoqinServiceExp;
	
	private Expression notiServiceExp;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		KaoqinService kaoqinService =(KaoqinService) kaoqinServiceExp.getValue(execution);
		Long businessId = (Long) execution.getVariableLocal(FlowConstant.BUSINESS_ID);
		
		Kaoqin kaoqin = kaoqinService.find(businessId);
		kaoqin.setState(KaoqinState.overdue);
		for(KaoqinItem item:kaoqin.getKaoqinItems()){
			item.setState(PassState.no);
		}
		kaoqinService.save(kaoqin);
		
		NotiService notiService=(NotiService) notiServiceExp.getValue(execution);
		String url=String.format("kaoqin/%s/view", kaoqin.getId());
		String fmturl=notiService.url(kaoqin.getTitle(), url);
		String message=String.format("单据%s已经超时！",fmturl);
		
		String fid = (String) execution.getVariable(FlowConstant.APPLY_USER);
		notiService.send(fid, message);
		
	}

}
