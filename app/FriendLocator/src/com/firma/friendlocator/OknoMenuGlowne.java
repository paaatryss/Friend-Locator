package com.firma.friendlocator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.firma.friendlocator.OknoMenu;
import com.google.android.maps.MapView;

public class OknoMenuGlowne extends Activity {
	static int i=0;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
				setContentView(R.layout.activity_okno_menu_glowne);
		
	}
	public void wyloguj(View view) {
		finish();
		Intent intent = new Intent(this, OknoLogowania.class);
	    startActivity(intent);
	}
	public void powrot(View view){
		Intent intent = new Intent(this, OknoMenu.class);
	    startActivity(intent);
	}
	public void zmien(View view){
		Intent intent = new Intent(this, OknoMenu.class);
		if(i==0){
		intent.putExtra("Value1", false);
		i=1;
		}
		else{
		intent.putExtra("Value1", true);
		i=0;
		}
	    startActivity(intent);
	}
	public void pomoc(View view){
		Intent intent = new Intent(this, OknoPomoc.class);
	    startActivity(intent);
	}
	public void ustawienia(View view){
		Intent intent = new Intent(this, OknoUstawienia.class);
	    startActivity(intent);
	}
	public void zaproszenia(View view){
		Intent intent = new Intent(this, OknoZaproszenia.class);
	    startActivity(intent);
	}
	public void autorzy(View view){
		Intent intent = new Intent(this, OknoAutorzy.class);
	    startActivity(intent);
	}
	@SuppressLint("NewApi")
	public void wyjdz(View view){
		finishAffinity();
	}
	public void znajomi(View view){
		Intent intent = new Intent(this, OknoZnajomi.class);
	    startActivity(intent);
	}

}
