package cn.fyg.kq.interfaces.web.module.adminkq.period;

import static cn.fyg.kq.interfaces.web.shared.message.Message.error;
import static cn.fyg.kq.interfaces.web.shared.message.Message.info;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.fyg.kq.application.ExcludeService;
import cn.fyg.kq.application.KaoqinService;
import cn.fyg.kq.application.PdtaskService;
import cn.fyg.kq.application.PeriodService;
import cn.fyg.kq.application.facade.KaoqinFacade;
import cn.fyg.kq.domain.model.kaoqin.KaoqinSpecs;
import cn.fyg.kq.domain.model.kaoqin.busi.Kaoqin;
import cn.fyg.kq.domain.model.kaoqin.busi.MonthItem;
import cn.fyg.kq.domain.model.pdtask.Pdtask;
import cn.fyg.kq.domain.model.pdtask.TaskState;
import cn.fyg.kq.domain.model.period.Period;
import cn.fyg.kq.domain.model.period.PeriodSpecs;
import cn.fyg.kq.domain.model.user.User;
import cn.fyg.kq.domain.shared.kq.Comp;
import cn.fyg.kq.interfaces.web.module.adminkq.period.facade.PeriodFacade;
import cn.fyg.kq.interfaces.web.shared.bean.YearAndPrevMonth;
import cn.fyg.kq.interfaces.web.shared.constant.AppConstant;
import cn.fyg.kq.interfaces.web.shared.session.SessionUtil;
import cn.fyg.kq.interfaces.web.shared.tool.DateTool;



@Controller
@RequestMapping("period")
@SessionAttributes("period_monthitem")
public class PeriodCtl {
	
	private static final String PATH = "period/";
	private interface Page {
		String LIST = PATH + "list";
		String CALRESULT = PATH + "calresult";
		String EXCEPT = PATH + "except";
	}
	
	@Autowired
	PeriodService periodService;
	@Autowired
	SessionUtil sessionUtil;
	@Autowired
	KaoqinService kaoqinService;
	@Autowired
	KaoqinFacade kaoqinFacade;
	@Autowired
	ExcludeService excludeService;
	@Autowired
	PeriodFacade periodFacade;

	@ModelAttribute("period_monthitem")
	public YearAndPrevMonth period_monthitem(){
		return new YearAndPrevMonth();
	}
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String toList(@ModelAttribute("period_monthitem")YearAndPrevMonth period_monthitem,Map<String,Object> map){
		User user=sessionUtil.getValue("user");
		Comp admincomp = user.getAdmincomp();
	
		Specifications<Period> specs=Specifications
				.where(PeriodSpecs.notFinish())
				.and(PeriodSpecs.inComp(admincomp));
		
		List<Period> periodList = this.periodService.findAll(specs);
		if(periodList.size()==1){
			Period period=periodList.get(0);
			map.put("period",period);
			boolean havePeriodTask = this.pdtaskService.havePeriodTask(period.getId());
			map.put("havePeriodTask", havePeriodTask);
			if(havePeriodTask){
				Pdtask pdtask = this.pdtaskService.get(period.getId());
				map.put("pdtask", pdtask);
			}
		}
		
		specs=Specifications
				.where(PeriodSpecs.isFinish())
				.and(PeriodSpecs.inComp(admincomp));
		List<Period> finishPeriodList = this.periodService.findAll(specs);
		map.put("finishPeriodList",finishPeriodList);
		
		map.put("dateTool", new DateTool());
		return Page.LIST;
	}
	
	
	@RequestMapping(value="create",method=RequestMethod.POST)
	public String create(@ModelAttribute("period_monthitem")YearAndPrevMonth period_monthitem,Map<String,Object> map,RedirectAttributes redirectAttributes){
		User user=sessionUtil.getValue("user");
		Comp admincomp = user.getAdmincomp();
		MonthItem monthItem = new MonthItem();
		monthItem.setYear(period_monthitem.getYear().intValue());
		monthItem.setMonth(period_monthitem.getMonth().intValue());
		boolean exist = this.periodService.exist(monthItem,admincomp);
		if(exist){
			redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, error("当前月份期间已经存在！"));
			return "redirect:list";
		}
		Period period = this.periodService.create(monthItem,admincomp);
		this.periodService.save(period);
		
		redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, info("保存成功！"));
		return "redirect:list";
	}
	
	@Autowired
	PdtaskService pdtaskService;
	
	@RequestMapping(value="docal",method=RequestMethod.POST)
	public String docal(@RequestParam("periodId") Long periodId,RedirectAttributes redirectAttributes){
		Pdtask pdtask = this.pdtaskService.create(periodId,"正在计算考勤结果···");
		this.pdtaskService.append(pdtask);
		this.periodFacade.calculate(periodId);
		redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, info("任务已启动！"));
		return "redirect:list";
	}
	
	@RequestMapping(value="{periodId}/calresult",method=RequestMethod.GET)
	public String toCalresult(@PathVariable("periodId") Long periodId,Map<String, Object> map) {
		Period period = this.periodService.find(periodId);
		map.put("period", period);

		Specification<Kaoqin> inPeriod = KaoqinSpecs.inPeriod(period);
		Specifications<Kaoqin> specs = Specifications.where(inPeriod);
		Sort sort=new Sort(Direction.ASC,"id");

		List<Kaoqin> kaoqinList = this.kaoqinService.findAll(specs, sort);
		map.put("kaoqinList", kaoqinList);
		return Page.CALRESULT;
	}
	
	@RequestMapping(value="{periodId}/except",method=RequestMethod.GET)
	public String toExcept(@PathVariable("periodId") Long periodId,Map<String, Object> map) {
		Period period = this.periodService.find(periodId);
		map.put("period", period);

		Specification<Kaoqin> inPeriod = KaoqinSpecs.inPeriod(period);
		Specification<Kaoqin> except = KaoqinSpecs.isExcept();

		Specifications<Kaoqin> specs = Specifications.where(inPeriod).and(except);
		Sort sort=new Sort(Direction.ASC,"id");

		List<Kaoqin> kaoqinList = this.kaoqinService.findAll(specs, sort);
		map.put("kaoqinList", kaoqinList);
		return Page.EXCEPT;
	}
	
		
	@RequestMapping(value="produce",method=RequestMethod.POST)
	public String produce(@RequestParam("periodId") Long periodId,RedirectAttributes redirectAttributes){
		User user = this.sessionUtil.getValue("user");
		Pdtask pdtask = this.pdtaskService.create(periodId,"正在生成考勤单···");
		this.pdtaskService.append(pdtask);
		this.periodFacade.produce(periodId, user);
		redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, info("任务已启动！"));
		return "redirect:list";
	}
	
	
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String delete(@RequestParam("periodId") Long periodId,RedirectAttributes redirectAttributes){
		Pdtask pdtask = this.pdtaskService.create(periodId,"正在删除考勤结果···");
		this.pdtaskService.append(pdtask);
		this.periodFacade.delete(periodId);
		redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, info("任务已启动！"));
		return "redirect:list";
	}

	@RequestMapping(value="canceltask",method=RequestMethod.POST)
	public String cancelTask(@RequestParam("periodId") Long periodId,RedirectAttributes redirectAttributes){
		Pdtask pdtask = this.pdtaskService.get(periodId);
		if (pdtask != null 
				&& pdtask.getState() == TaskState.start
				&& pdtask.getTimeout()) {
			this.pdtaskService.cancelPeriodTaks(periodId);
			redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, info("任务已经取消！"));
		}else{
			redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, error("任务取消失败，请联系管理员！"));
		}
		
		return "redirect:list";
	}
}
