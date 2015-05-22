package cn.fyg.kq.domain.model.kq.qingjia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.fyg.kq.domain.model.user.User;
import cn.fyg.kq.domain.shared.kq.Dayitem;

@Entity
@Table(name="kq_qingjia")
public class Qingjia {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; // 主键

	@Column(unique = true)
	private String no;// 编号

	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "fid")
	private User user;// 请假人员

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "date", column = @Column(name = "beg_date")),
			@AttributeOverride(name = "ampm", column = @Column(name = "beg_ampm")) })
	private Dayitem begDayitem;// 开始日期

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "date", column = @Column(name = "end_date")),
			@AttributeOverride(name = "ampm", column = @Column(name = "end_ampm")) })
	private Dayitem endDayitem;// 结束日期

	@Enumerated(EnumType.STRING)
	private QingjiaState state;// 状态 暂存-已提交-审批中-已完成 作废

	private int item_all; // 总条数

	private int item_real;// 实际条数

	private String reason;// 请假原因

	private String remark;// 备注

	@Temporal(TemporalType.TIMESTAMP)
	private Date createtime;// 创建时间

	private String comp;// 公司

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, targetEntity = QingjiaItem.class, orphanRemoval = true)
	@OrderBy("sn ASC")
	@JoinColumn(name = "qingjia_id")
	private List<QingjiaItem> qingjiaItems = new ArrayList<QingjiaItem>();
	
	@PrePersist
	private void prePersist(){
		this.createtime=new Date();
	}		

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Dayitem getBegDayitem() {
		return begDayitem;
	}

	public void setBegDayitem(Dayitem begDayitem) {
		this.begDayitem = begDayitem;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Dayitem getEndDayitem() {
		return endDayitem;
	}

	public void setEndDayitem(Dayitem endDayitem) {
		this.endDayitem = endDayitem;
	}

	public QingjiaState getState() {
		return state;
	}

	public void setState(QingjiaState state) {
		this.state = state;
	}

	public int getItem_all() {
		return item_all;
	}

	public void setItem_all(int item_all) {
		this.item_all = item_all;
	}

	public int getItem_real() {
		return item_real;
	}

	public void setItem_real(int item_real) {
		this.item_real = item_real;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getComp() {
		return comp;
	}

	public void setComp(String comp) {
		this.comp = comp;
	}

	public List<QingjiaItem> getQingjiaItems() {
		return qingjiaItems;
	}

	public void setQingjiaItems(List<QingjiaItem> qingjiaItems) {
		this.qingjiaItems = qingjiaItems;
	}
	
	

}
