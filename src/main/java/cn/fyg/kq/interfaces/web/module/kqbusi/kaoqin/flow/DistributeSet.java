package cn.fyg.kq.interfaces.web.module.kqbusi.kaoqin.flow;

import java.util.Arrays;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.commons.lang.StringUtils;

import com.google.common.base.Joiner;

import cn.fyg.kq.application.ReptlineService;
import cn.fyg.kq.application.ReptService.StatEnum;
import cn.fyg.kq.domain.model.reptline.Reptline;
import cn.fyg.kq.interfaces.web.shared.constant.FlowConstant;

public class DistributeSet implements JavaDelegate{

	private Expression reptlineServiceExp;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ReptlineService reptlineService=(ReptlineService) reptlineServiceExp.getValue(execution);
		
		String fid = (String) execution.getVariable(FlowConstant.APPLY_USER);
		
		Object done_node_obj = execution.getVariable("done_node");
		StatEnum done_node=done_node_obj==null?null:(StatEnum) done_node_obj;
		
		StatEnum require_node = (StatEnum) execution.getVariable("require_node");
		
		StatEnum next_node=next_node(reptlineService, fid, done_node, require_node,execution);
		execution.setVariable("done_node", next_node);
		
		
		String code=(String) execution.getVariable("reptline_code");
		String done_user=findUser(reptlineService,code,next_node);
		execution.setVariable("done_user", done_user);
		
	}

	private String findUser(ReptlineService reptlineService,String code, StatEnum next_node) {
		if(next_node==StatEnum.finish){
			return null;
		}
		String[] split = StringUtils.split(code,".");
		String[] splitTo=Arrays.copyOfRange(split, 0, next_node.level());
		Joiner joiner = Joiner.on(".").skipNulls();
		String newcode=joiner.join(splitTo);
		Reptline reptline = reptlineService.findByCode(newcode);
		
		return reptline.getUser().getFid();
	}

	private StatEnum next_node(ReptlineService reptlineService, String fid,
			StatEnum done_node, StatEnum require_node,DelegateExecution execution) {
		if(done_node == null){
			 Reptline reptline = reptlineService.maxUserLevel(fid);
			 execution.setVariable("reptline_code", reptline.getCode());
			 int level = reptline.getLevel();
			 StatEnum start_node = StatEnum.levelOf(level);
			 return start_node.next();
		 }

		 if(done_node.higher(require_node)){
			 return StatEnum.finish;
		 }
		 
		 return done_node.next();
	}

}
