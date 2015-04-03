package cn.fyg.kq.interfaces.web.modify;

public class User {
	
	private String fid;
	
	private String fnumber;
	
	private String fname;
	
	private String ftype;
	
	private boolean init;

	public User(String fid, String fnumber, String fname, String ftype) {
		super();
		this.fid = fid;
		this.fnumber = fnumber;
		this.fname = fname;
		this.ftype = ftype;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getFnumber() {
		return fnumber;
	}

	public void setFnumber(String fnumber) {
		this.fnumber = fnumber;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getFtype() {
		return ftype;
	}

	public void setFtype(String ftype) {
		this.ftype = ftype;
	}
	
	public boolean isInit() {
		return init;
	}

	public void setInit(boolean init) {
		this.init = init;
	}	

}
