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
		    Log.d("login:", "0");
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
			    	Log.d("login:", "1");
			    	JSONObject jObject = new JSONObject(builder.toString());
			    	Log.d("login:", "3");
			    	int error = jObject.optInt("error");
			    	Log.d("login:", "error: " + error);
			    	if(error != 1)
			    	{
			    		Log.d("login:", "4");
			    		String token = jObject.getString("token");
			    		Log.d("login:", "5");
			    				return token;
			    	}
			    	Log.d("login:", "6");
			    }catch(JSONException e){
			    		return "1";
			    }
			  return "1";
	}
	static public int register(String login,String haslo,String email){
		 StringBuilder builder = new StringBuilder();
		    HttpClient client = new DefaultHttpClient();
		    Log.d("register:", "0");
		    HttpGet httpGet = new HttpGet("http://flyer.kei.pl/friendlocator/register.php?login=" + login + "&password=" + haslo + "&email=" + email);
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
			    	Log.d("register:", "1");
			    	JSONObject jObject = new JSONObject(builder.toString());
			    	Log.d("register:", "3");
			    	int error = jObject.optInt("error");
			    	Log.d("register:", "error: " + error);
			    	if(error != 1)
			    	{
			    		return 0;
			    	}
			    	Log.d("register:", "6");
			    }catch(JSONException e){
			    		return 1;
			    }
			  return 1;
	}
}
