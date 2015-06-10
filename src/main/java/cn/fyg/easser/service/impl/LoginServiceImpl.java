package cn.fyg.easser.service.impl;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.Consts;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.fyg.easser.infrastructure.encrypt.DesUtil;
import cn.fyg.easser.service.LoginService;
import cn.fyg.easser.service.NetException;
import cn.fyg.kq.interfaces.web.modify.http.config.Cons;

@Service
public class LoginServiceImpl implements LoginService {
	
	public static final Logger logger=LoggerFactory.getLogger(LoginServiceImpl.class);
	

	@Override
	public String login(String username,String password) throws NetException, Exception{
		try {
			username = DesUtil.encryptDES(username);
			password = DesUtil.encryptDES(password);
			String str = Request.Post(Cons.EAS_URL)
			        .bodyForm(Form.form().add("username",username).add("password", password).build(),Consts.UTF_8)
			        .execute().returnContent().asString();
			
			str=DesUtil.decryptDES(str);
			JSONObject result = JSONObject.fromObject(str);
			if(result.getString("result").equals("TRUE")){
				return result.getString("fid");
			}
		} catch (ClientProtocolException e) {
			logger.error(null, e);
			throw new NetException("网络错误",e);
		} catch (IOException e) {
			logger.error(null, e);
			throw new NetException("网络错误",e);
		} catch (Exception e) {
			logger.error(null, e);
			throw new Exception("验证密码账号出错");
		}
		return null;
	}
	
	
}
