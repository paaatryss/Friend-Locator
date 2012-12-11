package com.firma.friendlocator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class OknoMenuGlowne extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
				setContentView(R.layout.activity_okno_menu_glowne);
		
	}
	public void wyjdz(View view) {
		int pid = android.os.Process.myPid();
		android.os.Process.killProcess(pid); 
	}
	public void wyloguj(View view){
		finish();
		Intent intent = new Intent(this, OknoLogowania.class);
	    startActivity(intent);
	}

}
