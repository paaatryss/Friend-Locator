package com.firma.friendlocator;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
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
		Friend test1 = new Friend("login1", 123, 123, 1);
		Friend test2 = new Friend("login2", 456, 456, 2);
		Friend test3 = new Friend("login1", 123, 123, 1);
		Friend test4 = new Friend("login2", 456, 456, 2);	
		Friend test5 = new Friend("login1", 123, 123, 1);
		Friend test6 = new Friend("login2", 456, 456, 2);
		Friend test7 = new Friend("login1", 123, 123, 1);
		Friend test8 = new Friend("login2", 456, 456, 2);
		Friend test9 = new Friend("login1", 123, 123, 1);
		Friend test10 = new Friend("login2", 456, 456, 2);	
		Friend test11 = new Friend("login1", 123, 123, 1);
		Friend test12 = new Friend("login2", 456, 456, 2);
		
		
		test1.setName("Marian");
		test2.setName("Dariusz");
		test3.setName("Marian");
		test4.setName("Dariusz");
		test5.setName("Marian");
		test6.setName("Dariusz");
		test7.setName("Marian");
		test8.setName("Dariusz");
		test9.setName("Marian");
		test10.setName("Dariusz");
		test11.setName("Marian");
		test12.setName("Dariusz");
		
		listItems.add(test1.getName() + ", " + test1.getLogin());
		listItems.add(test2.getName() + ", " + test2.getLogin());
		listItems.add(test3.getName() + ", " + test2.getLogin());
		listItems.add(test4.getName() + ", " + test2.getLogin());
		listItems.add(test5.getName() + ", " + test2.getLogin());
		listItems.add(test6.getName() + ", " + test2.getLogin());
		listItems.add(test7.getName() + ", " + test2.getLogin());
		listItems.add(test8.getName() + ", " + test2.getLogin());
		listItems.add(test9.getName() + ", " + test2.getLogin());
		listItems.add(test10.getName() + ", " + test2.getLogin());
		listItems.add(test11.getName() + ", " + test2.getLogin());
		listItems.add(test12.getName() + ", " + test2.getLogin());
		
		
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
