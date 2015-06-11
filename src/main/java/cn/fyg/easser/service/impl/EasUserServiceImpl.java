package cn.fyg.easser.service.impl;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.fyg.easser.domain.User;
import cn.fyg.easser.domain.config.Cons;
import cn.fyg.easser.domain.encrypt.DesUtil;
import cn.fyg.easser.service.EasUserService;
import cn.fyg.easser.service.NetException;

@Service
public class EasUserServiceImpl implements EasUserService {
	
	public static final Logger logger=LoggerFactory.getLogger(EasUserServiceImpl.class);
	
	@Override
	public List<User> query(String username) throws NetException,Exception{
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
			logger.error(null, e);
			throw new NetException("网络错误",e);
		} catch (IOException e) {
			logger.error(null, e);
			throw new NetException("网络错误",e);
		} catch (URISyntaxException e) {
			logger.error(null, e);
			throw new Exception("url解析错误",e);
		} catch (Exception e) {
			throw new Exception("方法错误",e);
		}
		
		return userList;
		
	}

}
