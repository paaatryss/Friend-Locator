package com.firma.friendlocator;

import com.example.friend_locator.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;

public class OknoRejestracji extends Activity {

	/** final EditText loginField = (EditText) findViewById(R.id.Login);  
	String login = loginField.getText().toString();  
	final EditText passwordField = (EditText) findViewById(R.id.Haslo);  
	String haslo = passwordField.getText().toString();   
	final EditText emailField = (EditText) findViewById(R.id.Haslo);  
	String email = emailField.getText().toString();
	final EditText email2Field = (EditText) findViewById(R.id.Haslo);  
	String email2 = email2Field.getText().toString(); **/
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okno_rejestracji);
    }
    
    public void login(View button) {
    	
    }
}