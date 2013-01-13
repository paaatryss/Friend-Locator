package com.firma.friendlocator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

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

public class ServerConnector {
	private String token;
	
	ServerConnector(String token)
	{
		this.token = token;
	}
	public ArrayList<Friend> GetFriends(String login,String haslo){
		 StringBuilder builder = new StringBuilder();
		    HttpClient client = new DefaultHttpClient();
		    HttpGet httpGet = new HttpGet("http://flyer.kei.pl/friendlocator/methods.php?call=getfrlocation&token=" + this.token);
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
     		        	return new ArrayList<Friend>();
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
			    		ArrayList<Friend> friends = new ArrayList<Friend>();
			    		JSONArray friendjarray = jArray.getJSONArray("friends");
			    		for(int i = 0; i < friendjarray.length(); i++){
			    			JSONObject rec = friendjarray.getJSONObject(i);
			    			Friend friend = new Friend(rec.getString("email"), rec.getString("login"), rec.getInt("lat"), rec.getInt("lng"), rec.getInt("id"));
			    			friends.add(friend);
			    		}
			    		return friends;
			    	}
			    	
			    }catch(JSONException e){
			    		    return new ArrayList<Friend>();
			    }
			  return new ArrayList<Friend>();
	}
}
