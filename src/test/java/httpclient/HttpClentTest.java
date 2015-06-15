package httpclient;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClentTest {
	

	@Test
	public void get(){
		try {
			String result = Request.Get("http://kh.fyg.cn:8080/pa/first").execute().returnContent().asString();
			System.out.println(result);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void post(){
		try {
			String result = Request.Post("http://kh.fyg.cn:8080/pa/login")
			        .bodyForm(Form.form().add("username", "admin").add("password", "fyg9900").build())
			        .execute().returnContent().asString();
			
			System.out.println(result);
		} catch (ClientProtocolException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void post2()throws Exception {

		 DefaultHttpClient client = new DefaultHttpClient();
		    client.setRedirectStrategy(new LaxRedirectStrategy());
		    Executor exec = Executor.newInstance(client);
		    String s = exec.execute(Request
		            .Post("http://kh.fyg.cn:8080/pa/login")
		            .bodyForm(Form.form().add("username", "admin").add("password", "fyg9900").build()))
		            .returnContent().asString();
		    System.out.println(s);
	}
	
	@Test
	public void post3() throws Exception{
		 CloseableHttpClient httpclient = HttpClients.createDefault();
		 HttpUriRequest login = RequestBuilder.post()
                 .setUri(new URI("http://kh.fyg.cn:8080/pa/login"))
                 .addParameter("username", "admin")
                 .addParameter("password", "fyg9900")
                 .build();
         CloseableHttpResponse response2 = httpclient.execute(login);
         try {
             HttpEntity entity = response2.getEntity();

             System.out.println("Login form get: " + response2.getStatusLine());
             EntityUtils.consume(entity);

         } finally {
             response2.close();
         }
	}
	
	@Test
	public void getandpost() throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet("http://targethost/homepage");
            CloseableHttpResponse response1 = httpclient.execute(httpGet);
            // The underlying HTTP connection is still held by the response object
            // to allow the response content to be streamed directly from the network socket.
            // In order to ensure correct deallocation of system resources
            // the user MUST call CloseableHttpResponse#close() from a finally clause.
            // Please note that if response content is not fully consumed the underlying
            // connection cannot be safely re-used and will be shut down and discarded
            // by the connection manager.
            try {
                System.out.println(response1.getStatusLine());
                HttpEntity entity1 = response1.getEntity();
                // do something useful with the response body
                // and ensure it is fully consumed
                EntityUtils.consume(entity1);
            } finally {
                response1.close();
            }

            HttpPost httpPost = new HttpPost("http://targethost/login");
            List <NameValuePair> nvps = new ArrayList <NameValuePair>();
            nvps.add(new BasicNameValuePair("username", "vip"));
            nvps.add(new BasicNameValuePair("password", "secret"));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse response2 = httpclient.execute(httpPost);

            try {
                System.out.println(response2.getStatusLine());
                HttpEntity entity2 = response2.getEntity();
                // do something useful with the response body
                // and ensure it is fully consumed
                EntityUtils.consume(entity2);
            } finally {
                response2.close();
            }
        } finally {
            httpclient.close();
        }
    }

}
