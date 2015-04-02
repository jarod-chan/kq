package cn.fyg.kq.interfaces.web.modify;

import org.springframework.stereotype.Component;

@Component
public class LoginHelp {
	
	public String encrypt(String username,String password){
		return null;
	}
	
	public String loginEAS(String encryptKey){
		return null;
	}
	
	public boolean isKQInit(String fid){
		return true;
	}
	
}
