package com.firma.friendlocator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.util.Log;

public class dataupdate {
	static Timer updateTimer = new Timer("gForceUpdate");
	static ArrayList<Friend> friends;
	
	
	public static void run2(){
		long k=0;
		long w=30000;

    updateTimer.scheduleAtFixedRate(new TimerTask() {

    public void run(){

    	Log.d("updateqqq","updateqqq");
    	ServerConnector test = new ServerConnector(OknoLogowania.getToken());
		friends = new ArrayList<Friend>();
		friends = test.GetFriends();
		OknoMenu.draw();


    }

    }, k, w);

}
}
