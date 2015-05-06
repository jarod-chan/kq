package cn.fyg.kq.domain.model.kq.tag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bs_tag")
public class Tag {
	
	@Id
	@Column(name="fkey")
	private String key;//	关键字
	
	private String name;//	标签名
	
	private String tagtype;//	类型id

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

	public String getTagtype() {
		return tagtype;
	}

	public void setTagtype(String tagtype) {
		this.tagtype = tagtype;
	}

	
}
