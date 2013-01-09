package com.firma.friendlocator;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

public class OknoWyslijEmail extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
				setContentView(R.layout.activity_okno_wyslij_email);		
	}
	public void send(View view) {
		EditText loginField = (EditText) findViewById(R.id.tytul);  
    	String tytul = loginField.getText().toString(); 
    	EditText loginField2 = (EditText) findViewById(R.id.tresc);  
    	String tresc = loginField2.getText().toString(); 
		Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, tytul);
		intent.putExtra(Intent.EXTRA_TEXT, tresc);
		intent.setData(Uri.parse("mailto:baszo33@op.pl")); // or just "mailto:" for blank
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
		startActivity(intent);
	}

}
