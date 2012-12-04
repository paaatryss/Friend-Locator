package com.firma.friendlocator;

import com.firma.friendlocator.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class OknoRejestracji extends Activity {
	Context context;

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
    	int check;
    	EditText loginField = (EditText) findViewById(R.id.text_login_rejestracja);  
    	String login = loginField.getText().toString(); 
    	final EditText passwordField = (EditText) findViewById(R.id.text_haslo_rejestracja);  
    	String haslo = passwordField.getText().toString();
    	final EditText emailField = (EditText) findViewById(R.id.text_email_rejestracja);  
    	String email = emailField.getText().toString();
    	check = Auth.register(login,haslo,email);
    	if(check==0){
    		Intent intent = new Intent(this, OknoMenu.class);
    	    startActivity(intent);
    	}
    	else if(check == 1){
    		context = getApplicationContext();
    		Toast.makeText(context, "niepopoprawny login bπdü has≥o!", Toast.LENGTH_LONG).show();
    		
    	}
    	else if(check == 2){
    		context = getApplicationContext();
    		Toast.makeText(context, "b≥πs po≥πczenia!", Toast.LENGTH_LONG).show();
    	}
    	else{
    		context = getApplicationContext();
    		Toast.makeText(context, "error "+check +"!", Toast.LENGTH_LONG).show();
    	}
    }
}