package com.firma.friendlocator;


import com.firma.friendlocator.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class OknoLogowania extends Activity {
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
				setContentView(R.layout.activity_okno_logowania);
		
	}
	
	public void zaloguj(View view) {
		int check;
		EditText loginField = (EditText) findViewById(R.id.text_login);  
    	String login = loginField.getText().toString(); 
    	final EditText passwordField = (EditText) findViewById(R.id.text_haslo);  
    	String haslo = passwordField.getText().toString();   
    	check = Auth.login(login,haslo);
    	if(check==0){
    		Intent intent = new Intent(this, OknoMenu.class);
    	    startActivity(intent);
    	}
    	else if(check == 1){
    		context = getApplicationContext();
    		Toast.makeText(context, "niepopoprawny login bπdü has≥o!", Toast.LENGTH_LONG).show();
    		
    	}
    	else if(check == 2){
    		context = getApplicationContext();
    		Toast.makeText(context, "b≥πs po≥πczenia!", Toast.LENGTH_LONG).show();
    	}
    	else{
    		context = getApplicationContext();
    		Toast.makeText(context, "error "+check +"!", Toast.LENGTH_LONG).show();
    	}
	}
        
	public void zarejestruj(View view) {
	    Intent intent = new Intent(this, OknoRejestracji.class);
	    startActivity(intent);
	}
        
	public void zapomnialem(View view) {
	    Intent intent = new Intent(this, OknoZapomnialemDanych.class);
	    startActivity(intent);
	} 
}
