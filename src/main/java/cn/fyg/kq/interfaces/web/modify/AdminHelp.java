package cn.fyg.kq.interfaces.web.modify;

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

@Component
public class AdminHelp {
	
	public List<User> query(String username){
		List<User> userList = new ArrayList<User>();
		try {
			URI uri = new URIBuilder("http://127.22.1.30:3000/queryuser").addParameter("fname",
					username).build();
			String result = Request
					.Get(uri)
					.execute().returnContent().asString();
			JSONArray jsonArray = JSONArray.fromObject(result);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject userJson = jsonArray.getJSONObject(i);
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
		}
		
		return userList;
		
	}

}
