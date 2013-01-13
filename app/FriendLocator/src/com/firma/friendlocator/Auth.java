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

import android.util.Log;

public class Auth {
	static public String login(String login,String haslo){
		 StringBuilder builder = new StringBuilder();
		    HttpClient client = new DefaultHttpClient();
		    Log.d("info:", "0");
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
      		        	return "2";
      		      }
			    } catch (ClientProtocolException e) {
			      e.printStackTrace();
			    } catch (IOException e) {
			      e.printStackTrace();
			    }
			    try{
			    	Log.d("info:", "1");
			    	JSONObject jObject = new JSONObject(builder.toString());
			    	Log.d("info:", "3");
			    	int error = jObject.optInt("error");
			    	Log.d("info:", "error: " + error);
			    	if(error != 1)
			    	{
			    		Log.d("info:", "4");
			    		String token = jObject.getString("token");
			    		Log.d("info:", "5");
			    				return token;
			    	}
			    	Log.d("info:", "6");
			    }catch(JSONException e){
			    		return "1";
			    }
			  return "1";
	}
	static public int register(String login,String haslo,String email){
		return 0;
	}
}
