package cn.fyg.kq.domain.model.exclude;

import java.util.Date;

import cn.fyg.kq.domain.shared.kq.Ampm;
import cn.fyg.kq.domain.shared.kq.Dayitem;

public class ExcludeFactory {
	
	public static Exclude create(Long period_id,Date date,Ampm ampm){
		Exclude exclude = new Exclude();
		Dayitem dayitem = new Dayitem();
		dayitem.setDate(date);
		dayitem.setAmpm(ampm);
		exclude.setPeriod_id(period_id);
		exclude.setDayitem(dayitem);
		return exclude;
	}

}
