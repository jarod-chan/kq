package cn.fyg.kq.domain.model.pdtask;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *period执行异步任务时记录
 */
@Entity
@Table(name="kq_pdtask")
public class Pdtask {
	
	@Id
	private Long period_id;//考勤期间id
	
	private String taskname;//任务描述
	
	@Enumerated(EnumType.STRING)
	private TaskState state;//状态
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createtime;// 创建时间
	


	public Long getPeriod_id() {
		return period_id;
	}

	public void setPeriod_id(Long period_id) {
		this.period_id = period_id;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public TaskState getState() {
		return state;
	}

	public void setState(TaskState state) {
		this.state = state;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Boolean getTimeout() {
		return this.state==TaskState.start
				&& new Date().getTime()-this.createtime.getTime()>3*60*1000;
	}
	
	

}
