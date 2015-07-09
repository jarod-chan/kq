package cn.fyg.kq.domain.model.modmenu.menu;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="kq_menu")
public class Menu implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private String sn;
	
	private String name;
	
	private String url;
	

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

}
