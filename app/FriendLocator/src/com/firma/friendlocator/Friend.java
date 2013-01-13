package com.firma.friendlocator;

import java.util.ArrayList;

public class Friend {
	  private int id;
	  private String login;
	  private String name;
	  private int longitude;
	  private int latitude;
	  
	  public ArrayList<Friend> friends = new ArrayList<Friend>();

	public Friend(String login, String name, int latitude, int longitude, int id){
		this.id = id;
		this.login = login;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getId() {
		return this.id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public String getLogin() {
		return this.login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getLatitude() {
		return this.latitude;
	}
	
	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}
	
	public int getLongitude() {
		return this.longitude;
	}
	
	public void setLongitude(int longitude) {
		this.latitude = longitude;
	}
	
/*	public void getFriendsFromServer() {
		for(int i = 0; i < leszkowaLista.size(); i++){
			friends(i).setName(leszkowaLista(i).name);
			friends(i).setLogin(leszkowaLista(i).login);
			friends(i).setLatitude(leszkowaLista(i).latitude);
			friends(i).setLongitude(leszkowaLista(i).longitude);
			friends(i).setId(leszkowaLista(i).id);
		}
	} */
	
/*	public String getFriendStringForList(int friendId) {
		String listString = friends(friendId).name + ", " + friends(friendId).login;
		return listString;
	} */
	
}
