package clientmethods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.http.Header;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.api.test.TestBase;
import org.api.utility.IteratorUtility;
import org.json.JSONArray;
import org.json.JSONObject;

public class RestClient {
	List<List<HashMap<Object,Object>>> list;
	List<HashMap<Object, Object>> listOfMap;
	HashMap<Object, Object> jsonArray;
	HashMap<Object, Object> jsonObject;
	List<HashMap<Object,Object>> listOfJsonArray;
	public List<List<HashMap<Object,Object>>> getMethod(String url) {
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
			
			list=new ArrayList<List<HashMap<Object,Object>>>();
			jsonArray = new HashMap<Object, Object>();
			jsonObject = new HashMap<Object, Object>();
			listOfJsonArray =new ArrayList<HashMap<Object,Object>>();
	Iterator<String> itr = json.keys();		
 while (itr.hasNext()) {
				String key = itr.next();
				System.out.println(key);
				System.out.println(json.get(key));
				Object objValue=json.get(key);
				String str = objValue.toString();
				System.out.println(str);
				
				if (str.contains("[")) {
				
					JSONArray array = (JSONArray) objValue;
					System.out.println("This is JsonArray" + array);
					for (int i = 0; i < array.length(); i++) {
						JSONObject resObj = array.getJSONObject(i);
						Iterator<String> newKey = resObj.keys();
						while (newKey.hasNext()) {
							jsonArray=new HashMap<Object, Object>();
							listOfJsonArray=new ArrayList<HashMap<Object,Object>>();
							String nk = newKey.next();
							String value = resObj.get(nk).toString();
							System.out.println(value);
							jsonArray.put(nk, value);
							listOfJsonArray.add(jsonArray);
							list.add(listOfJsonArray);
						}
					}

				} else {
					jsonObject.put(key, str);
				}
				
			}

			
			
			
/*			Iterator<String> itr = json.keys();
			jsonArray = new HashMap<Object, Object>();
			jsonObject = new HashMap<Object, Object>();
			while (itr.hasNext()) {
				String key=IteratorUtility.iteratorValue(json, itr);
				String str = IteratorUtility.jsonParser(json, itr);
				Object objValue=json.get(key);
				System.out.println(str);
				
				if (str.contains("[")) {
					//System.out.println(itr.next().toString());
					JSONArray array = (JSONArray)objValue;
					System.out.println("This is JsonArray" + array);
					for (int i = 0; i < array.length(); i++) {
						JSONObject resObj = array.getJSONObject(i);
						Iterator<String> newKey = resObj.keys();
						while (newKey.hasNext()) {
							String nk = newKey.next();
							String value=IteratorUtility.jsonParser(resObj, newKey);
							jsonArray.put(nk, value);

						}
					}

				} else {
					jsonObject.put(key, str);
				}

			}
*/			listOfMap = new ArrayList<HashMap<Object, Object>>();
			listOfMap.add(jsonObject);
			
			list.add(listOfMap);
			
			HashMap<String, String> allheaders = new HashMap<String, String>();
			for (Header header : headerArray) {
				allheaders.put(header.getName(), header.getValue());
			}
			System.out.println(allheaders);
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(list);
		return list;
	}
}
