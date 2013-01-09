package com.firma.friendlocator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.firma.friendlocator.OknoMenu;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class OknoZmianaHasla extends Activity {
	Context context;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
				setContentView(R.layout.activity_okno_zmiana_hasla);
		
	}
	int check=0;
	public void zamien(View view){
    	final EditText passwordField = (EditText) findViewById(R.id.wpisz_stare_haslo);  
    	String haslo = passwordField.getText().toString();
    	final EditText passwordField2 = (EditText) findViewById(R.id.wpisz_nowe_haslo);  
    	String haslo2 = passwordField2.getText().toString();
    	//Auth.check(haslo,haslo2);
    	if(check==0){
    		context = getApplicationContext();
    		Toast.makeText(context, "Has³o zosta³o zmienione", Toast.LENGTH_LONG).show();
    		Intent intent = new Intent(this, OknoUstawienia.class);
    	    startActivity(intent);
    	}
    	if(check==1){
    		context = getApplicationContext();
    		Toast.makeText(context, "Ÿle wpisane dane", Toast.LENGTH_LONG).show();
    	}
	}
}
