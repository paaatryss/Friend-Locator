package com.firma.friendlocator;

import java.util.ArrayList;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
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
	Context context,box;
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
				box=(Context) this;
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
		lista1.setOnItemLongClickListener(new OnItemLongClickListener(){


	        public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
	                long arg3) {
	            // TODO Auto-generated method stub
	        	int check=0;
	        	if(check==0){
	  	    	  AlertDialog.Builder builder = new AlertDialog.Builder(box);
	  	      	builder
	  	      	.setTitle("Chcesz napewno chcesz usun¹æ znajomego")
	  	      	.setMessage("Jesteœ pewien?")
	  	      	.setIcon(android.R.drawable.ic_dialog_alert)
	  	      	.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
	  	      		@SuppressLint("NewApi")
	  				public void onClick(DialogInterface dialog, int which) {			      	
	  	      	    	//Yes button clicked, do something
		        		context = getApplicationContext();
		        		Toast.makeText(context, "Znajomy usuniety", Toast.LENGTH_LONG).show();
		        		 recreate ();
	  	      	    }
	  	      	})
	  	      	.setNegativeButton("Nie", null)						//Do nothing on no
	  	      	.show();
	        	}
	        	if(check==1){
	        		context = getApplicationContext();
	        		Toast.makeText(context, "b³¹d", Toast.LENGTH_LONG).show();
	        	}
	        	return true;
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
