package com.firma.friendlocator;

import android.app.Activity;
import android.content.Intent;

import com.firma.friendlocator.OknoMenu;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class OknoUstawienia extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
				setContentView(R.layout.activity_okno_ustawienia);
		
	}
	public void zmiana(View view){
		Intent intent = new Intent(this, OknoZmianaHasla.class);
		startActivity(intent);

	}
	public void zmianaemail(View view){
		Intent intent = new Intent(this, OknoZmianaEmail.class);
	    startActivity(intent);
	}

}
