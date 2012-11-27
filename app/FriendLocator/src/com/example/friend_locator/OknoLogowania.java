package com.example.friend_locator;

import android.os.Bundle;
import com.google.android.maps.MapActivity;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class OknoLogowania extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_okno_logowania);
		
	}
	
	public void zaloguj(View view) {
	    Intent intent = new Intent(this, OknoMenu.class);
	    startActivity(intent);
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

