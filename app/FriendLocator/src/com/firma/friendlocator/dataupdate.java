package com.firma.friendlocator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.util.Log;

public class dataupdate {
	static Timer updateTimer = null;
	static ArrayList<Friend> friends;
	static ServerConnector test=null;
	public static long k=0;
	public static long w=30000;
	
	
	public static void run2(){
		updateTimer=new Timer("gForceUpdate");

    updateTimer.scheduleAtFixedRate(new TimerTask() {

    public void run(){

    	Log.d("updateqqq","updateqqq");
    	 test = new ServerConnector(OknoLogowania.getToken());
		friends = new ArrayList<Friend>();
		friends = test.GetFriends();
		OknoMenu.draw();


    }

    }, k, w);

}
}
