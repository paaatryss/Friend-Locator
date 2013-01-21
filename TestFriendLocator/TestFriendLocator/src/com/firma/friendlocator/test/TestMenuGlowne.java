package com.firma.friendlocator.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.TextView;

import junit.framework.Assert;
import junit.framework.TestCase;
import com.jayway.android.robotium.solo.Solo;
import com.firma.friendlocator.Auth;
import com.firma.friendlocator.OknoMenuGlowne;

public class TestMenuGlowne extends ActivityInstrumentationTestCase2<OknoMenuGlowne> {
	private Solo solo;

	public TestMenuGlowne() {
		super("com.firma.friendlocator", OknoMenuGlowne.class);
	}

	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void test(){
	    solo.clickOnButton("Zaproszenia");
	    solo.assertCurrentActivity("Expected activity", "OknoZaproszenia");
	    solo.goBack();
	    solo.clickOnButton("Autorzy");
	    solo.assertCurrentActivity("Expected activity", "OknoAutorzy");
	    solo.goBack();
	    solo.clickOnButton("Ustawienia");
	    solo.assertCurrentActivity("Expected activity", "OknoUstawienia");
	    solo.goBack();
	    solo.clickOnButton("Pomoc");
	    solo.assertCurrentActivity("Expected activity", "OknoPomoc");
	    solo.goBack();
	    
	}
	public void test2(){
		solo.clickOnButton("Zaproszenia");
		solo.assertCurrentActivity("Expected activity", "OknoZaproszenia");
	    solo.goBack();
	}
	public void test3(){
	    solo.clickOnButton("Ustawienia");
	    solo.assertCurrentActivity("Expected activity", "OknoUstawienia");
	    solo.clickOnButton("Zmieñ has³o");
	    solo.assertCurrentActivity("Expected activity", "OknoZmianaHasla");
	    solo.goBack();
	    solo.goBack();
	}
	
	public void test4(){
	    solo.clickOnButton("Ustawienia");
	    solo.assertCurrentActivity("Expected activity", "OknoUstawienia");
	    solo.clickOnButton("Zmieñ email");
	    solo.assertCurrentActivity("Expected activity", "OknoZmianaEmail");
	    solo.goBack();
	    solo.goBack();
	}
	public void test5(){
	    solo.clickOnButton("Pomoc");
	    solo.assertCurrentActivity("Expected activity", "OknoPomoc");
	    solo.clickOnButton("Wyœlij do nas maila");
	    solo.assertCurrentActivity("Expected activity", "OknoWyslijEmail");
	    solo.goBack();
	    solo.clickOnButton("Jak zmieniæ has³o");
	    solo.assertCurrentActivity("Expected activity", "OknoPomocyZmianyHasla");
	}
	public void test6(){
	    solo.clickOnButton("Pomoc");
	    solo.assertCurrentActivity("Expected activity", "OknoPomoc");
	    solo.clickOnButton("Jak zmieniæ email");
	    solo.assertCurrentActivity("Expected activity", "OknoPomocyZmianyEmail");
	}
	public void test7(){
	    solo.clickOnButton("Ustawienia");
	    solo.assertCurrentActivity("Expected activity", "OknoUstawienia");
	    solo.clickOnButton("Zmieñ has³o");
	    solo.assertCurrentActivity("Expected activity", "OknoZmianaHasla");
	    solo.goBack();
	    solo.goBack();
	    solo.clickOnButton("Pomoc");
	    solo.assertCurrentActivity("Expected activity", "OknoPomoc");
	    solo.clickOnButton("Jak zmieniæ email");
	    solo.assertCurrentActivity("Expected activity", "OknoPomocyZmianyEmail");
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
