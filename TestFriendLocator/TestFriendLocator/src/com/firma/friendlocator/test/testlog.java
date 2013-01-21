package com.firma.friendlocator.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.TextView;

import junit.framework.Assert;
import junit.framework.TestCase;
import com.jayway.android.robotium.solo.Solo;
import com.firma.friendlocator.Auth;
import com.firma.friendlocator.OknoMenuGlowne;
import com.firma.friendlocator.OknoLogowania;
import com.firma.friendlocator.dataupdate;
import com.firma.friendlocator.R;

public class testlog extends ActivityInstrumentationTestCase2<OknoLogowania> {
	private Solo solo;
	private Activity activity;

	public testlog() {
		super("com.firma.friendlocator", OknoLogowania.class);
	}

	protected void setUp() throws Exception {
		this.activity = this.getActivity();
		solo = new Solo(getInstrumentation(), this.activity);
	}
	
	public void test(){
		this.solo.clearEditText((EditText) this.activity.findViewById(R.id.text_login));
		this.solo.clearEditText((EditText) this.activity.findViewById(R.id.text_haslo));
		this.solo.enterText((EditText) this.activity.findViewById(R.id.text_login), "test");
		this.solo.enterText((EditText) this.activity.findViewById(R.id.text_haslo), "franek");
	    solo.clickOnButton("Zaloguj siê");
	    solo.assertCurrentActivity("Expected activity", "OknoMenu");
	    
	}
	 public void tearDown() throws Exception {

		    try {
		      solo.finalize();
		    } catch (Throwable e) {

		      e.printStackTrace();
		    }
		    getActivity().finish();
		    super.tearDown();

		  }

}
