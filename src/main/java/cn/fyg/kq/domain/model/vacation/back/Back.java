package cn.fyg.kq.domain.model.vacation.back;

import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.fyg.kq.domain.model.vacation.common.BusiState;
import cn.fyg.kq.domain.model.vacation.common.Dayitem;
import cn.fyg.kq.domain.model.vacation.common.LeaveType;
import cn.fyg.kq.domain.model.vacation.leave.Leave;
import cn.fyg.module.user.User;
import cn.fyg.module.user.impl.domain.UserEntity;

/**
 * 销假业务
 */
@Entity
@Table(name="hr_back")
public class Back {
	
	public static final String BUSINESS_CODE="HR-XJ";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="leave_id")
	private Leave leave;
	
	@Column(unique=true)
	private String no;//序号
	
	@Enumerated(EnumType.STRING)
	private LeaveType leaveType;//请假类别
	
	@Column(length=256)
	private String description;//说明
	
	@Embedded
	@AttributeOverrides({
	    @AttributeOverride(name="date", column=@Column(name="beg_date")),
	    @AttributeOverride(name="ampm", column=@Column(name="beg_ampm"))
	})
	private Dayitem begDayitem;//开始日期
	
	@Embedded
	@AttributeOverrides({
		 @AttributeOverride(name="date", column=@Column(name="end_date")),
		 @AttributeOverride(name="ampm", column=@Column(name="end_ampm"))
	})
	private Dayitem endDayitem;//结束日期
	
	@Column(precision=6, scale=2)
	private BigDecimal natureDay;//自然天数
	
	@Column(precision=6, scale=2)
	private BigDecimal acturlDay;//实际天数
	
	@Column
	@Enumerated(EnumType.STRING)
	private BusiState busiState;//状态
	
	@ManyToOne(targetEntity=UserEntity.class)
	@JoinColumn(name="user_")
	private User user;//销假人员
	
	@Column(name="date_")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;//日期
	
	@PrePersist
	private void prePersist(){
		this.date=new Date();
	}
	
	/**
	 * 判断当前销假单是否正常的销假单据
	 * 正常：1销假单包含对应请假单 2销假日期在对应请假日期之内
	 * @return
	 */
	public Boolean isNormalBack(){
		if(this.leave!=null){
			return this.begDayitem.getDate().compareTo(this.leave.getBegDayitem().getDate())>=0
					&& this.endDayitem.getDate().compareTo(this.leave.getEndDayitem().getDate())<=0;
		}
		return Boolean.FALSE;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Leave getLeave() {
		return leave;
	}

	public void setLeave(Leave leave) {
		this.leave = leave;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public LeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Dayitem getBegDayitem() {
		return begDayitem;
	}

	public void setBegDayitem(Dayitem begDayitem) {
		this.begDayitem = begDayitem;
	}

	public Dayitem getEndDayitem() {
		return endDayitem;
	}

	public void setEndDayitem(Dayitem endDayitem) {
		this.endDayitem = endDayitem;
	}

	public BigDecimal getNatureDay() {
		return natureDay;
	}

	public void setNatureDay(BigDecimal natureDay) {
		this.natureDay = natureDay;
	}

	public BigDecimal getActurlDay() {
		return acturlDay;
	}

	public void setActurlDay(BigDecimal acturlDay) {
		this.acturlDay = acturlDay;
	}

	public BusiState getBusiState() {
		return busiState;
	}

	public void setBusiState(BusiState busiState) {
		this.busiState = busiState;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
