package com.firma.friendlocator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class OknoDodawania extends Activity {
	Context context;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
				setContentView(R.layout.activity_okno_dodawania);		
	} 
	//final EditText emailField = (EditText) findViewById(R.id.text_email_znajomy);
	int check=0;
	 public void add(View button) { 
			//String email = emailField.getText().toString();
			//check=Auth.dodajznajomego(email);
    		context = getApplicationContext();
    		Toast.makeText(context, "Dodano znajomego!", Toast.LENGTH_LONG).show();
			
		 
	 }

}
