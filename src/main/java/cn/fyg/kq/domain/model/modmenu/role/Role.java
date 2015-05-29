package cn.fyg.kq.domain.model.modmenu.role;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import cn.fyg.kq.domain.model.modmenu.menu.Menu;

/**
 *系统角色
 */
@Entity
@Table(name="kq_role")
public class Role {
	
	@Id
	@Column(name="fkey")
	private String key;//关键字
	
	private String name;//名称
	
	private String remark;//说明，用户角色的内容
	
	@ManyToMany(fetch = FetchType.LAZY)//优化查询
	@JoinTable(name="kq_rolemenu",joinColumns=@JoinColumn(name="role_key"),inverseJoinColumns=@JoinColumn(name="menu_sn"))
	@OrderBy("sn ASC")
	private List<Menu> menus = new ArrayList<Menu>();

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


}
