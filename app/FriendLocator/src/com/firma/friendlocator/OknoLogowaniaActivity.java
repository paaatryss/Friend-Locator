package com.firma.friendlocator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
test


public class OknoLogowaniaActivity extends Activity {

	final EditText loginField = (EditText) findViewById(R.id.Login);  
	String login = loginField.getText().toString();  
	final EditText passwordField = (EditText) findViewById(R.id.Haslo);  
	String haslo = passwordField.getText().toString();   
	final CheckBox responseCheckbox = (CheckBox) findViewById(R.id.ZapamietajLogin);  
	boolean zapamietajLogin = responseCheckbox.isChecked();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okno_logowania);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.okno_logowania, menu);
        return true;
    }
    
    public void login(View button) {
    	
    }
}
