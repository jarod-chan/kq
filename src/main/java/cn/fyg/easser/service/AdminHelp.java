package cn.fyg.easser.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Component;

import cn.fyg.easser.infrastructure.encrypt.DesUtil;
import cn.fyg.kq.interfaces.web.modify.User;
import cn.fyg.kq.interfaces.web.modify.http.config.Cons;


public class AdminHelp {
	
	public List<User> query(String username){
		List<User> userList = new ArrayList<User>();
		try {
			username=DesUtil.encryptDES(username);
			URI uri = new URIBuilder(Cons.EAS_URL).addParameter("fname",
					username).build();
			String str = Request
					.Get(uri)
					.execute().returnContent().asString();
			str=DesUtil.decryptDES(str);
			JSONObject result = JSONObject.fromObject(str);
			JSONArray datas = result .getJSONArray("datas");
			for (int i = 0; i < datas.size(); i++) {
				JSONObject userJson = datas.getJSONObject(i);
				userList.add(new User(userJson.getString("fid"),
						userJson.getString("fnumber"),
						userJson.getString("fname"),
						userJson.getString("ftype")));
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userList;
		
	}

}
