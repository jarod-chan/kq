package cn.fyg.kq.interfaces.web.module.adminkq.period.exclude;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTime.Property;

import cn.fyg.kq.domain.model.exclude.Exclude;
import cn.fyg.kq.domain.model.exclude.ExcludeFactory;
import cn.fyg.kq.domain.model.kaoqin.busi.MonthItem;
import cn.fyg.kq.domain.model.period.Period;
import cn.fyg.kq.domain.shared.kq.Ampm;

public class ExcludePage {
	
	private List<ExcludeBean> execludeBeans=new ArrayList<ExcludeBean>();

	public List<ExcludeBean> getExecludeBeans() {
		return execludeBeans;
	}

	public void setExecludeBeans(List<ExcludeBean> execludeBeans) {
		this.execludeBeans = execludeBeans;
	}
	
	public void init(Period period,List<Exclude> dbExcludeList){
		this.allMonthExcludeBean(period);
		this.setCheckedItem(dbExcludeList);
	}
	
	private void allMonthExcludeBean(Period period){
		Long period_id = period.getId();
		MonthItem monthitem = period.getMonthitem();
		//获得一个月的每一天
		DateTime dateTime = new DateTime(monthitem.getYear(), monthitem.getMonth(), 1, 0, 0, 0, 0);
		Property dayOfMonth = dateTime.dayOfMonth();
		int maxdays = dayOfMonth.getMaximumValue();
		for (int i = 1; i < maxdays; i++) {
			Date date = new DateTime(monthitem.getYear(),monthitem.getMonth(), i, 0, 0, 0, 0).toDate();
			Exclude exclude = ExcludeFactory.create(period_id, date, Ampm.am);
			this.execludeBeans.add(new ExcludeBean(exclude,false));
			exclude = ExcludeFactory.create(period_id, date, Ampm.pm);
			this.execludeBeans.add(new ExcludeBean(exclude,false));
		}
		
	}
	
	private void setCheckedItem(List<Exclude> dbExcludeList){
		for(ExcludeBean excludeBean:this.execludeBeans){
			for(Exclude exclude:dbExcludeList){
				if(excludeBean.sameDayitem(exclude)){
					excludeBean.setExclude(exclude);
					excludeBean.setChecked(true);
				}
			}
		}
	}
	
}
