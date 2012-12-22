package com.firma.friendlocator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Auth {
	static public int login(String login,String haslo){
		 StringBuilder builder = new StringBuilder();
		    HttpClient client = new DefaultHttpClient();
		    HttpGet httpGet = new HttpGet("http://flyer.kei.pl/friendlocator/login.php?login=" + login + "&password=" + haslo);
		    try {
			      HttpResponse response = client.execute(httpGet);
			      StatusLine statusLine = response.getStatusLine();
			      int statusCode = statusLine.getStatusCode();
      		      if (statusCode == 200) {
      		        HttpEntity entity = response.getEntity();
      		        InputStream content = entity.getContent();
      		        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
      		        String line;
      		        while ((line = reader.readLine()) != null) {
      		          builder.append(line + "\n");
      		        }
      		      } else {
      		        	return 2;
      		      }
			    } catch (ClientProtocolException e) {
			      e.printStackTrace();
			    } catch (IOException e) {
			      e.printStackTrace();
			    }
			    try{
			    	JSONObject jArray = new JSONObject(builder.toString());
			    	JSONArray error = jArray.getJSONArray("error");
			    	if(error.length() == 0)
			    	{
			    		JSONArray token = jArray.getJSONArray("token");
			    				return 0;
			    	}
			    	
			    }catch(JSONException e){
			    		    return 0;
			    }
			  return 1;
	}
	static public int register(String login,String haslo,String email){
		return 0;
	}
}
