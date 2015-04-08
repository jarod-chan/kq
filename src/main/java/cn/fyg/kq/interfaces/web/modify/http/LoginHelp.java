package cn.fyg.kq.interfaces.web.modify.http;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.Consts;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.fyg.kq.application.KQUserService;
import cn.fyg.kq.interfaces.web.modify.http.config.Cons;

@Component
public class LoginHelp {
	
	@Autowired
	KQUserService kqUserService;
	
	public String encrypt(String username,String password){
		return null;
	}
	
	public String loginEAS(String username,String password){
		try {
			username = DesUtil.encryptDES(username);
			password = DesUtil.encryptDES(password);
			String str = Request.Post(Cons.EAS_URL)
			        .bodyForm(Form.form().add("username",username).add("password", password).build(),Consts.UTF_8)
			        .execute().returnContent().asString();
			
			str=DesUtil.decryptDES(str);
			JSONObject result = JSONObject.fromObject(str);
			if(result.getBoolean("result")){
				return result.getString("fid");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean isKQInit(String fid){
		return kqUserService.isInit(fid);
	}
	
}
