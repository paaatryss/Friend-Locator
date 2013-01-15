package com.firma.friendlocator;

import java.util.ArrayList;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;


import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class OknoZnajomi extends Activity{
	private ListView lista1;
	private ArrayList<String> listItems = new ArrayList<String>();
	private ArrayAdapter<String> adapter;
	ArrayList<Friend> friends = new ArrayList<Friend>();
	
	protected void onCreate(Bundle savedInstanceState) {		
		ServerConnector test = new ServerConnector("c576526171df6f92db16fc1c2cbf1dc0");
		friends = test.GetFriends();
		Log.d("ilosc frendow", Integer.toString(friends.size()));
		for(int i=0; i<friends.size(); i++)
		{
			Friend fr = friends.get(i);
			listItems.add(fr.getName() + ", " + fr.getLogin());
		}
		
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
				setContentView(R.layout.activity_okno_znajomi);
				setUpView();
	}
	
	private void setUpView(){
		lista1 = (ListView)this.findViewById(R.id.listView1);
		lista1.setClickable(true);
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
		lista1.setAdapter(adapter);

		lista1.setOnItemClickListener(new OnItemClickListener(){


	        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
	                long arg3) {
	            // TODO Auto-generated method stub
	        	Friend fr = friends.get(arg2);
	        	MapController mapController = OknoMenu.mapView.getController();
	        	GeoPoint point2 = new GeoPoint(fr.getLatitude(), fr.getLongitude());
	        	ss();
	        	mapController.animateTo(point2);
	        }

	    });

	}
	
	public void ss(){
		Intent intent2 = new Intent(this, OknoMenu.class);
	    startActivity(intent2);
	}
	public void dodaj(View view) {
		Intent intent = new Intent(this, OknoDodawania.class);
	    startActivity(intent);
	}

}
