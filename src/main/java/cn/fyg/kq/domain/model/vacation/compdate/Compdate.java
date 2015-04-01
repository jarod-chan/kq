package cn.fyg.kq.domain.model.vacation.compdate;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.fyg.kq.domain.model.vacation.common.Dayitem;

/**
 *公司假期，用于排除请假时间里的公共假期等
 */
@Entity
@Table(name="hr_compdate")
public class Compdate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Embedded
	@Column(unique=true)
	@AttributeOverrides({
	    @AttributeOverride(name="date", column=@Column(name="vacation_date")),
	    @AttributeOverride(name="ampm", column=@Column(name="vacation_ampm"))
	})
	private Dayitem vacation;//公司假期 
	
	@Enumerated(EnumType.STRING)
	private Datastate datastate;//数据状态
	
	@Enumerated(EnumType.STRING)
	private WaitAction waitAction;//数据更改状态
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Dayitem getVacation() {
		return vacation;
	}

	public void setVacation(Dayitem vacation) {
		this.vacation = vacation;
	}

	public Datastate getDatastate() {
		return datastate;
	}

	public void setDatastate(Datastate datastate) {
		this.datastate = datastate;
	}

	public WaitAction getWaitAction() {
		return waitAction;
	}

	public void setWaitAction(WaitAction waitAction) {
		this.waitAction = waitAction;
	}

}
