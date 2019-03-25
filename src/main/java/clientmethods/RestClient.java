package clientmethods;

import java.util.HashMap;

import org.apache.http.Header;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.api.test.TestBase;
import org.json.JSONObject;

public class RestClient {

	public void getMethod(String url) {
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpGet get = new HttpGet(url);
			CloseableHttpResponse response = httpClient.execute(get);
			int statusCode = response.getStatusLine().getStatusCode();
			System.out.println(statusCode);
			String res = EntityUtils.toString(response.getEntity(), "UTF-8");
			System.out.println(res);
			JSONObject json = new JSONObject(res);
			System.out.println(json);
			Header[] headerArray = response.getAllHeaders();
			HashMap<String, String> allheaders = new HashMap<String, String>();
			for (Header header : headerArray) {
				allheaders.put(header.getName(), header.getValue());
			}
			System.out.println(allheaders);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
