package com.firma.friendlocator;


import com.firma.friendlocator.R;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class OknoLogowania extends Activity {
	Context context;
	public CheckBox zapiszDane;
	private static final String PREFERENCES_LOGIN = "login";
    private static final String PREFERENCES_PASSWORD = "haslo";
    private SharedPreferences preferences;
    public EditText loginField;
    public EditText passwordField;
    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (android.os.Build.VERSION.SDK_INT > 9) {
		      StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		      StrictMode.setThreadPolicy(policy);
		   }
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
				setContentView(R.layout.activity_okno_logowania);
		preferences = getSharedPreferences(PREFERENCES_LOGIN, Activity.MODE_PRIVATE);
		restoreData();
		
	}
	
	public void zaloguj(View view) {
		String check;
		EditText loginField = (EditText) findViewById(R.id.text_login);  
    	String login = loginField.getText().toString(); 
    	final EditText passwordField = (EditText) findViewById(R.id.text_haslo);  
    	String haslo = passwordField.getText().toString();   
    	
    	check = Auth.login(login,haslo);
    	Log.d("info", "login zwraca:"+check);
    	if(check.equals("1")){
    		context = getApplicationContext();
    		Toast.makeText(context, "niepopoprawny login bπdü has≥o!", Toast.LENGTH_LONG).show();
    		
    	}
    	else if(check.equals("2")){
    		context = getApplicationContext();
    		Toast.makeText(context, "b≥πs po≥πczenia!", Toast.LENGTH_LONG).show();
    	}
    	else{
    		zapiszDane = (CheckBox) findViewById(R.id.checkBox_zapamietajDane);
    		 if (zapiszDane.isChecked()) {
    			 saveData();
    			 showToast("Dane Zapisane");
             }
    		Intent intent = new Intent(this, OknoMenu.class);
    		tmp.intent=intent;
    	    startActivity(intent);
    	}
	}
    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
        
	public void zarejestruj(View view) {
	    Intent intent = new Intent(this, OknoRejestracji.class);
	    startActivity(intent);
	}
        
	public void zapomnialem(View view) {
	    Intent intent = new Intent(this, OknoZapomnialemDanych.class);
	    startActivity(intent);
	} 
	private void saveData() {
		loginField = (EditText) findViewById(R.id.text_login);  
    	String login = loginField.getText().toString(); 
    	passwordField = (EditText) findViewById(R.id.text_haslo);  
    	String haslo = passwordField.getText().toString();  
    	
	    SharedPreferences.Editor preferencesEditor = preferences.edit();
	    preferencesEditor.putString(PREFERENCES_LOGIN, login);
	    preferencesEditor.putString(PREFERENCES_PASSWORD, haslo);
	    preferencesEditor.commit();
	}
	
	private void restoreData() {
	    String loginFromPreferences = preferences.getString(PREFERENCES_LOGIN, "");
	    String passwordFromPreferences = preferences.getString(PREFERENCES_PASSWORD, "");
	    loginField = (EditText) findViewById(R.id.text_login);
	    passwordField = (EditText) findViewById(R.id.text_haslo);
	    loginField.setText(loginFromPreferences);
	    passwordField.setText(passwordFromPreferences);
	}
}
