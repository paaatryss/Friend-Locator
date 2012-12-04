package com.firma.friendlocator;

import com.firma.friendlocator.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

public class OknoRejestracji extends Activity {

	/*EditText loginField = (EditText) findViewById(R.id.text_login_rejestracja);  
	String login = loginField.getText().toString();  
	final EditText passwordField = (EditText) findViewById(R.id.Haslo);  
	String haslo = passwordField.getText().toString();   
	final EditText emailField = (EditText) findViewById(R.id.Haslo);  
	String email = emailField.getText().toString();
	final EditText email2Field = (EditText) findViewById(R.id.Haslo);  
	String email2 = email2Field.getText().toString(); */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_okno_rejestracji);
    }
    
    public void login(View button) {
    	EditText loginField = (EditText) findViewById(R.id.text_login_rejestracja);  
    	String login = loginField.getText().toString(); 
    }
}