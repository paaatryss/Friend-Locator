package com.firma.friendlocator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class OknoZnajomi extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
				setContentView(R.layout.activity_okno_znajomi);		
	}
	public void dodaj(View view) {
		Intent intent = new Intent(this, OknoDodawania.class);
	    startActivity(intent);
	}

}
