package cn.fyg.kq.domain.model.period;

import cn.fyg.kq.domain.model.kaoqin.busi.MonthItem;
import cn.fyg.kq.domain.shared.kq.Comp;

public class PeriodFactory {
	
	public static Period create(MonthItem monthItem,Comp comp){
		Period period = new Period();
		period.setMonthitem(monthItem);
		period.setState(PeriodState.create);
		period.setComp(comp);
		return period;
	}

}
