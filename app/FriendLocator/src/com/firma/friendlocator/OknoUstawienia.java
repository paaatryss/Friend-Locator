package com.firma.friendlocator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.firma.friendlocator.OknoMenu;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class OknoUstawienia extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
				setContentView(R.layout.activity_okno_ustawienia);
		
	}
	public void zmiana(View view){
		Intent intent = new Intent(this, OknoZmianaHasla.class);
		startActivity(intent);

	}
	public void zmianaemail(View view){
		Intent intent = new Intent(this, OknoZmianaEmail.class);
	    startActivity(intent);
	}
	public void time(View view){
		
		EditText fp1 = (EditText) findViewById(R.id.editText1);  
		String f1 = fp1.getText().toString();
		if (f1.length()==0){
			Context context;
			context = getApplicationContext();
    		Toast.makeText(context, "Empty Data", Toast.LENGTH_LONG).show();
		}
		else if(Integer.parseInt(fp1.getText().toString())<=0){
			Context context;
			context = getApplicationContext();
    		Toast.makeText(context, "Ujemna wartoœæ", Toast.LENGTH_LONG).show();
		}
		else
		{
		int fpw;
		fpw = Integer.parseInt(fp1.getText().toString());
		Log.d("zmiana", Integer.toString(fpw*1000));
		dataupdate.updateTimer.cancel();
		dataupdate.updateTimer.purge();
		dataupdate.updateTimer=null;
		dataupdate.w=fpw*1000;
		dataupdate.run2();
		Context context;
		context = getApplicationContext();
		Toast.makeText(context, "Waroœæ zmieniona", Toast.LENGTH_LONG).show();
		}
	}

}
