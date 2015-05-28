package cn.fyg.kq.domain.model.modmenu.menu;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.fyg.kq.domain.model.modmenu.module.Module;

@Entity
@Table(name="kq_menu")
public class Menu {
	
	@Id
	private String sn;
	
	private String name;
	
	private String url;
	
	@ManyToOne(targetEntity=Module.class)
	@JoinColumn(name="module_sn")
	private Module module;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}
	
	

}
