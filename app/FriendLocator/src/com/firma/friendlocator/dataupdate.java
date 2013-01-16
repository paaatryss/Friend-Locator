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
		long w=10000;

    updateTimer.scheduleAtFixedRate(new TimerTask() {

    public void run(){

    	Log.d("updateqqq","updateqqq");
    	ServerConnector test = new ServerConnector("c576526171df6f92db16fc1c2cbf1dc0");
		friends = new ArrayList<Friend>();
		friends = test.GetFriends();
		OknoMenu.u=1;
		
		//mCallback.onArticleSelected(3);

    }

    }, k, w);

}
}
