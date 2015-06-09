package cn.fyg.kq.domain.model.kaoqin.busi;

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
import cn.fyg.kq.domain.shared.process.ProcessObj;

@Entity
@Table(name="kq_kaoqin")
public class Kaoqin implements ProcessObj {
	
	public static final String BUSI_CODE = "KQ";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; // 主键	
	
	@Column(unique = true)
	private String no;// 编号	
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "fid")
	private User user;// 考勤人员
	
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "year", column = @Column(name = "kq_year")),
			@AttributeOverride(name = "month", column = @Column(name = "kq_month")) })
	MonthItem monthitem;
	
	@Enumerated(EnumType.STRING)
	KaoqinState state;//	状态			已生成-已提交-审批中-已完成 作废
	
	private int item_all; // 总条数

	private int item_real;// 通过条数	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createtime;// 创建时间

	private String comp;// 公司
	
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, targetEntity = KaoqinItem.class, orphanRemoval = true)
	@OrderBy("sn ASC")
	@JoinColumn(name = "kaoqin_id")
	private List<KaoqinItem> kaoqinItems = new ArrayList<KaoqinItem>();
	
	protected String processId;//流程id，根据单据查找对应的流程
	
	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

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

	public MonthItem getMonthitem() {
		return monthitem;
	}

	public void setMonthitem(MonthItem monthitem) {
		this.monthitem = monthitem;
	}

	public KaoqinState getState() {
		return state;
	}

	public void setState(KaoqinState state) {
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

	public List<KaoqinItem> getKaoqinItems() {
		return kaoqinItems;
	}

	public void setKaoqinItems(List<KaoqinItem> kaoqinItems) {
		this.kaoqinItems = kaoqinItems;
	}


	@Override
	public String getTitle() {
		return String.format("%d年%d月%s考勤单", 
				this.getMonthitem().getYear(), 
				this.getMonthitem().getMonth(), 
				this.getUser().getFnumber()); 
	}
	
	
	
}
