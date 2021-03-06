package com.firma.friendlocator;

import com.firma.friendlocator.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class OknoRejestracji extends Activity {
	Context context;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_okno_rejestracji);
    }
    int check;
	String checka;
    public void login(View button) {  
    	EditText loginField = (EditText) findViewById(R.id.text_login_rejestracja);
    	final EditText passwordField = (EditText) findViewById(R.id.text_haslo_rejestracja);
    	final EditText emailField = (EditText) findViewById(R.id.text_email_rejestracja);
    	String login = loginField.getText().toString();  
    	String haslo = passwordField.getText().toString();     
    	String email = emailField.getText().toString();     	
    	check = Auth.register(login,haslo,email);
    	if(check==0){
        	checka = Auth.login(login,haslo);
        	//Log.d("info", "login zwraca:"+check);
        	if(checka.equals("1")){
        		context = getApplicationContext();
        		Toast.makeText(context, "niepopoprawny login b�d� has�o!", Toast.LENGTH_LONG).show();
        		
        	}
        	else if(checka.equals("2")){
        		context = getApplicationContext();
        		Toast.makeText(context, "b��s po��czenia!", Toast.LENGTH_LONG).show();
        	}
        	else{
        		OknoLogowania.saveToken(checka);
        		Intent intent = new Intent(this, OknoMenu.class);
        	    startActivity(intent);
        	}
    	}
    	else if(check == 1){
    		context = getApplicationContext();
    		Toast.makeText(context, "niepopoprawny login haslo lub email!", Toast.LENGTH_LONG).show();
    		
    	}
    	else if(check == 2){
    		context = getApplicationContext();
    		Toast.makeText(context, "b��s po��czenia!", Toast.LENGTH_LONG).show();
    	}
    	else{
    		context = getApplicationContext();
    		Toast.makeText(context, "error "+check +"!", Toast.LENGTH_LONG).show();
    	}
    }
}