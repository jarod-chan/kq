package cn.fyg.kq.domain.model.modmenu.module;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import cn.fyg.kq.domain.model.modmenu.menu.Menu;

@Entity
@Table(name="kq_module")
public class Module {
	
	@Id
	private String sn;
	
	private String name;
	
	@OneToMany(fetch = FetchType.EAGER,targetEntity = Menu.class)
	@JoinColumn(name="module_sn")
	@OrderBy("sn ASC")
	private List<Menu> menus=new ArrayList<Menu>();

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
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
	
	

}
