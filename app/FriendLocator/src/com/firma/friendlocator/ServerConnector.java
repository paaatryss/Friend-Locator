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

import android.util.Log;

public class ServerConnector {
	private String token;
	
	ServerConnector(String token)
	{
		this.token = token;
	}
	
	public ArrayList<Friend> GetFriends(){
		 StringBuilder builder = new StringBuilder();
		    HttpClient client = new DefaultHttpClient();
		    HttpGet httpGet = new HttpGet("http://flyer.kei.pl/friendlocator/methods.php?call=getfrlocation&token=" + this.token);
		    Log.d("ServerConnector", "1");
		    try {
			      HttpResponse response = client.execute(httpGet);
			      StatusLine statusLine = response.getStatusLine();
			      int statusCode = statusLine.getStatusCode();
			      Log.d("ServerConnector", "2");
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
     		     Log.d("ServerConnector", "3");
			    } catch (ClientProtocolException e) {
			      e.printStackTrace();
			    } catch (IOException e) {
			      e.printStackTrace();
			    }
			    try{
			    	Log.d("ServerConnector", "4");
			    	JSONArray jArray = new JSONArray(builder.toString());
			    	Log.d("ServerConnector", "przed pobraniem");
			    	ArrayList<Friend> friends = new ArrayList<Friend>();
			    	Log.d("ServerConnector", "friendjarray.length() " + Integer.toString(jArray.length()));
			    		
			    	for(int i = 0; i < jArray.length(); i++){
			    			JSONObject rec = jArray.getJSONObject(i);
			    			Log.d("importuje friend", rec.getString("login"));
			    			Friend friend = new Friend(rec.getString("email"), rec.getString("login"), rec.getInt("lat"), rec.getInt("lng"), rec.getInt("id"));
			    			friends.add(friend);
			    	}
			    	return friends;
			    }catch(JSONException e){
			    		    return new ArrayList<Friend>();
			    }
	}
	public int SendMyLocation(int lat, int lng){
		 StringBuilder builder = new StringBuilder();
		    HttpClient client = new DefaultHttpClient();
		    HttpGet httpGet = new HttpGet("http://flyer.kei.pl/friendlocator/methods.php?call=sendmylocation&token=" + this.token + "&lat=" + lat + "&lng=" + lng);
		    Log.d("SendMyLocation", "1");
		    try {
			      HttpResponse response = client.execute(httpGet);
			      StatusLine statusLine = response.getStatusLine();
			      int statusCode = statusLine.getStatusCode();
			      Log.d("SendMyLocation", "2");
    		      if (statusCode == 200) {
    		        HttpEntity entity = response.getEntity();
    		        InputStream content = entity.getContent();
    		        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
    		        String line;
    		        while ((line = reader.readLine()) != null) {
    		          builder.append(line + "\n");
    		        }
    		      } else {
    		        	return 1;
    		      }
    		     Log.d("SendMyLocation", "3");
			    } catch (ClientProtocolException e) {
			      e.printStackTrace();
			    } catch (IOException e) {
			      e.printStackTrace();
			    }
			    try{
			    	Log.d("SendMyLocation:", "1");
			    	JSONObject jObject = new JSONObject(builder.toString());
			    	Log.d("SendMyLocation:", "3");
			    	int error = jObject.optInt("error");
			    	Log.d("SendMyLocation:", "error: " + error);
			    	if(error != 1)
			    	{
			    		return 0;
			    	}
			    	Log.d("SendMyLocation:", "6");
			    }catch(JSONException e){
			    		return 1;
			    }
		return 1;
		}
	public ArrayList<String> GetInvitations(){
		StringBuilder builder = new StringBuilder();
	    HttpClient client = new DefaultHttpClient();
	    HttpGet httpGet = new HttpGet("http://flyer.kei.pl/friendlocator/methods.php?call=getinvitations&token=" + this.token);
	    Log.d("GetInvitations", "1");
	    try {
		      HttpResponse response = client.execute(httpGet);
		      StatusLine statusLine = response.getStatusLine();
		      int statusCode = statusLine.getStatusCode();
		      Log.d("GetInvitations", "2");
		      if (statusCode == 200) {
		        HttpEntity entity = response.getEntity();
		        InputStream content = entity.getContent();
		        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
		        String line;
		        while ((line = reader.readLine()) != null) {
		          builder.append(line + "\n");
		        }
		      } else {
		        	return new ArrayList<String>();
		      }
		     Log.d("GetInvitations", "3");
		    } catch (ClientProtocolException e) {
		      e.printStackTrace();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		try{
		    	Log.d("GetInvitations:", "4");
		    	
		    	JSONArray jArray = new JSONArray(builder.toString());
		    	Log.d("GetInvitations", "5");
		    	ArrayList<String> invitations = new ArrayList<String>();
		    	Log.d("GetInvitations", "jArray.length() " + Integer.toString(jArray.length()));
		    	
		    	for(int i = 0; i < jArray.length(); i++){
	    			JSONObject rec = jArray.getJSONObject(i);
	    			Log.d("importuje zaproszenie", rec.getString("inv"));
	    			invitations.add(rec.getString("inv"));
		    	}
		    	return invitations;
		    }catch(JSONException e){
		    		return new ArrayList<String>();
		}
	}
	
	/**
	 * Akceptuje zaproszenie, podaj login lub email jako String
	 * @param String text
	 * @return
	 */
	public int AcceptInv(String text){
		 StringBuilder builder = new StringBuilder();
		    HttpClient client = new DefaultHttpClient();
		    HttpGet httpGet = new HttpGet("http://flyer.kei.pl/friendlocator/methods.php?call=acceptinv&token=" + this.token + "&from=" + text);
		    Log.d("AcceptInv", "1");
		    try {
			      HttpResponse response = client.execute(httpGet);
			      StatusLine statusLine = response.getStatusLine();
			      int statusCode = statusLine.getStatusCode();
			      Log.d("AcceptInv", "2");
   		      if (statusCode == 200) {
   		        HttpEntity entity = response.getEntity();
   		        InputStream content = entity.getContent();
   		        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
   		        String line;
   		        while ((line = reader.readLine()) != null) {
   		          builder.append(line + "\n");
   		        }
   		      } else {
   		        	return 1;
   		      }
   		     Log.d("AcceptInv", "3");
			    } catch (ClientProtocolException e) {
			      e.printStackTrace();
			    } catch (IOException e) {
			      e.printStackTrace();
			    }
			    try{
			    	Log.d("AcceptInv:", "4");
			    	JSONObject jObject = new JSONObject(builder.toString());
			    	Log.d("AcceptInv:", "5");
			    	int error = jObject.optInt("error");
			    	Log.d("AcceptInv:", "error: " + error);
			    	if(error != 1)
			    	{
			    		return 0;
			    	}
			    	Log.d("AcceptInv:", "6");
			    }catch(JSONException e){
			    		return 1;
			    }
		return 1;
		}
	
	
	/**
	 * Odrzuca zaproszenie, podaj login lub email jako String
	 * @param String text
	 * @return
	 */
	public int RejectInv(String text){
		 StringBuilder builder = new StringBuilder();
		    HttpClient client = new DefaultHttpClient();
		    HttpGet httpGet = new HttpGet("http://flyer.kei.pl/friendlocator/methods.php?call=rejectinv&token=" + this.token + "&from=" + text);
		    Log.d("RejectInv", "1");
		    try {
			      HttpResponse response = client.execute(httpGet);
			      StatusLine statusLine = response.getStatusLine();
			      int statusCode = statusLine.getStatusCode();
			      Log.d("RejectInv", "2");
   		      if (statusCode == 200) {
   		        HttpEntity entity = response.getEntity();
   		        InputStream content = entity.getContent();
   		        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
   		        String line;
   		        while ((line = reader.readLine()) != null) {
   		          builder.append(line + "\n");
   		        }
   		      } else {
   		        	return 1;
   		      }
   		     Log.d("RejectInv", "3");
			    } catch (ClientProtocolException e) {
			      e.printStackTrace();
			    } catch (IOException e) {
			      e.printStackTrace();
			    }
			    try{
			    	Log.d("RejectInv:", "4");
			    	JSONObject jObject = new JSONObject(builder.toString());
			    	Log.d("RejectInv:", "5");
			    	int error = jObject.optInt("error");
			    	Log.d("RejectInv:", "error: " + error);
			    	if(error != 1)
			    	{
			    		return 0;
			    	}
			    	Log.d("RejectInv:", "6");
			    }catch(JSONException e){
			    		return 1;
			    }
		return 1;
		}
	
}
