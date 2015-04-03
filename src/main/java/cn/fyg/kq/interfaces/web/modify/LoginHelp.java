package cn.fyg.kq.interfaces.web.modify;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.Consts;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.fyg.kq.application.KQUserService;

@Component
public class LoginHelp {
	
	@Autowired
	KQUserService kqUserService;
	
	public String encrypt(String username,String password){
		return null;
	}
	
	public String loginEAS(String username,String password){
		try {
			String str = Request.Post("http://127.22.1.30:3000/authuser")
			        .bodyForm(Form.form().add("username",username).add("password", password).build(),Consts.UTF_8)
			        .execute().returnContent().asString();
			
			JSONObject result = JSONObject.fromObject(str);
			if(result.getBoolean("result")){
				return result.getString("fid");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean isKQInit(String fid){
		return kqUserService.isInit(fid);
	}
	
}
