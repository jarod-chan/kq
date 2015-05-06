package cn.fyg.kq.domain.model.kq.tagtype;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bs_tagtype")
public class Tagtype {
	
	@Id
	@Column(name="fkey")
	private String key;//	关键字
	
	private String name;//	名称

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

	

}
