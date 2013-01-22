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
	EditText emailField;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
				setContentView(R.layout.activity_okno_dodawania);		
	} 
	int check=0;
	 public void add(View button) { 
		 	emailField=(EditText) findViewById(R.id.text_email_znajomy);
			String email = emailField.getText().toString();
    		context = getApplicationContext();
    		ServerConnector test = new ServerConnector(OknoLogowania.getToken());
    		check=test.SendInv(email);
    		if(check==0){
    			Toast.makeText(context, "Wys≥ano zaproszenie!", Toast.LENGTH_LONG).show();
    		}
    		else{
    			Toast.makeText(context, "B≥Ídny email/login bπdü problem z serwerem", Toast.LENGTH_LONG).show();
    		}
		 
	 }

}
