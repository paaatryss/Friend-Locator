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
	public void wyloguj(View view) {
		finish();
		Intent intent = new Intent(this, OknoLogowania.class);
	    startActivity(intent);
	}
	public void powrot(View view){
		finish();
		Intent intent = new Intent(this, OknoMenu.class);
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
	public void znajomi(View view){
		Intent intent = new Intent(this, OknoZnajomi.class);
	    startActivity(intent);
	}

}
