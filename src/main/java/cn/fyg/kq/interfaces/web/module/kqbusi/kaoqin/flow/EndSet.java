package cn.fyg.kq.interfaces.web.module.kqbusi.kaoqin.flow;

import java.util.List;

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

public class EndSet implements JavaDelegate {
	
	private Expression kaoqinServiceExp;
	
	private Expression notiServiceExp;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		KaoqinService kaoqinService =(KaoqinService) kaoqinServiceExp.getValue(execution);
		Long businessId = (Long) execution.getVariableLocal(FlowConstant.BUSINESS_ID);
		
		Kaoqin kaoqin = kaoqinService.find(businessId);
		kaoqin.setState(KaoqinState.finish);
		List<KaoqinItem> kaoqinItems = kaoqin.getKaoqinItems();
		if(!kaoqinItems.isEmpty()){
			int item_real=0;
			for(KaoqinItem item:kaoqinItems){
				if(item.getState()==PassState.accept){
					item_real++;
				}
			}
			kaoqin.setItem_real(item_real);
		}
		kaoqinService.save(kaoqin);
		
		NotiService notiService=(NotiService) notiServiceExp.getValue(execution);
		String url=String.format("kaoqin/%s/view", kaoqin.getId());
		String fmturl=notiService.url(kaoqin.getTitle(), url);
		String message=String.format("单据%s已经审批完成！",fmturl);
		if(kaoqin.getItem_real()<kaoqin.getItem_all()){
			message+=String.format("有%s条不被认可。",kaoqin.getItem_all()-kaoqin.getItem_real());
		}
		
		String fid = (String) execution.getVariable(FlowConstant.APPLY_USER);
		notiService.send(fid, message);
	}

}
