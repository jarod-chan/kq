package cn.fyg.kq.interfaces.web.module.adminkq.period.exclude;

import cn.fyg.kq.domain.model.exclude.Exclude;

public class ExcludeBean {
	
	private Exclude exclude;
	
	private boolean checked;

	public Exclude getExclude() {
		return exclude;
	}

	public void setExclude(Exclude exclude) {
		this.exclude = exclude;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	public ExcludeBean(){}

	public ExcludeBean(Exclude exclude, boolean checked) {
		super();
		this.exclude = exclude;
		this.checked = checked;
	}
	
	public boolean sameDayitem(Exclude anotherExclude){
		if(this.exclude==null) return false;
		if(anotherExclude==null) return false;
		return this.exclude.getDayitem().equals(anotherExclude.getDayitem());
	}
	
	

}
