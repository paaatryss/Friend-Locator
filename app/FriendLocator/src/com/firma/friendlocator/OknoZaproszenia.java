package com.firma.friendlocator;

import java.util.ArrayList;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class OknoZaproszenia extends Activity {
	private ListView lista1;
	public static ArrayAdapter<String> adapter;
	public static  ArrayList<String> listItems;
	ArrayList<String> inw;
	String nick;
	Context context,box;
	ArrayList<Friend> friends;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
				setContentView(R.layout.activity_okno_zaproszenia);
				listItems= new ArrayList<String>();
				inw=dataupdate.test.GetInvitations();
				for(int i=0; i<inw.size(); i++)
				{
					nick=inw.get(i);
					listItems.add(nick);
				}
				setUpView();
				box=(Context) this;
		
	}
	private void setUpView(){
		lista1 = (ListView)this.findViewById(R.id.listView1);
		lista1.setClickable(true);
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
		lista1.setAdapter(adapter);

		lista1.setOnItemClickListener(new OnItemClickListener(){


	        public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2,
	                long arg3) {
	            // TODO Auto-generated method stub
	        	AlertDialog.Builder builder = new AlertDialog.Builder(box);
	  	      	builder
	  	      	.setTitle("Chcesz napewno chcesz usun¹æ znajomego")
	  	      	.setMessage("Jesteœ pewien?")
	  	      	.setIcon(android.R.drawable.ic_dialog_alert)
	  	      	.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
	  	      		@SuppressLint("NewApi")
	  				public void onClick(DialogInterface dialog, int which) {			      	
	  	      	    	//Yes button clicked, do something
	  	      			ServerConnector test = new ServerConnector(OknoLogowania.getToken());
	  	      			ServerConnector test2 = new ServerConnector(OknoLogowania.getToken());
		        		context = getApplicationContext();
		        		test.AcceptInv(listItems.get(arg2));
		        		OknoMenu.list.clear();
		        		OknoMenu.dataAdapter.notifyDataSetChanged();
		        		OknoZnajomi.listItems.clear();
		        		OknoZnajomi.adapter.notifyDataSetChanged();
		        		listItems.clear();
		        		adapter.notifyDataSetChanged();
		        		friends= new ArrayList<Friend>();
		        		friends = test.GetFriends();
		        		dataupdate.friends=friends;
		        		OknoMenu.draw();
		        		inw= new ArrayList<String>();
		        		inw = test2.GetInvitations();
		        		for(int i=0; i<=friends.size(); i++)
		        		{
		        			if(i==0){
		        				OknoMenu.list.add("Znajomi:");	
		        			}
		        			else{
		        			Friend fr = friends.get(i-1);
		        			OknoMenu.list.add(fr.getName());
		        			OknoZnajomi.listItems.add(fr.getName() + ", " + fr.getLogin());
		        			}
		        		}
		    			for(int i=0; i<inw.size(); i++)
						{
							nick=inw.get(i);
							listItems.add(nick);
						}
		        		Toast.makeText(context, "Znajomy dodany", Toast.LENGTH_LONG).show();
		        		OknoZnajomi.adapter.notifyDataSetChanged();
		        		adapter.notifyDataSetChanged();
		        		OknoMenu.dataAdapter.notifyDataSetChanged();
	  	      	    }
	  	      	})
	  	      	      .setNeutralButton("Zignoruj",new DialogInterface.OnClickListener() {
	  	      	    	  public void onClick(DialogInterface dialog, int whichButton){
	  	      	    		ServerConnector test = new ServerConnector(OknoLogowania.getToken());
	  	      	    		test.RejectInv(listItems.get(arg2));
	  	      	    	  }
	  	      	      	})

	  	      	.setNegativeButton("Poczekaj", null)						//Do nothing on no
	  	      	.show();

	        }

	    });

	}

}
