package com.firma.friendlocator;

import java.util.ArrayList;


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
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class OknoZnajomi extends Activity{
	private ListView lista1;
	private ArrayList<String> listItems = new ArrayList<String>();
	private ArrayAdapter<String> adapter;
	
	protected void onCreate(Bundle savedInstanceState) {		
		ServerConnector test = new ServerConnector("c576526171df6f92db16fc1c2cbf1dc0");
		ArrayList<Friend> friends = new ArrayList<Friend>();
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
	}
	
	
	public void dodaj(View view) {
		Intent intent = new Intent(this, OknoDodawania.class);
	    startActivity(intent);
	}

}
