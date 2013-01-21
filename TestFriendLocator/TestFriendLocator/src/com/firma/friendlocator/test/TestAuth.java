package com.firma.friendlocator.test;

import com.firma.friendlocator.Auth;

import junit.framework.Assert;
import junit.framework.TestCase;


public class TestAuth extends TestCase {

	public TestAuth(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void test(){
		String login = "";
		String password = "";
		Assert.assertEquals("1", Auth.login(login, password));
	}

	public void test2(){
		String login = "admin";
		String password = "franekdup";
		Assert.assertEquals("1", Auth.login(login, password));
	}
	
	public void test3(){
		String login = "test";
		String password = "franek";
		String k=Auth.login(login, password);
		String spr="0";
		if(k.length()>20){
			spr="1";
		}
		Assert.assertEquals("1",spr );
	}
	
	public void test4(){
		String login = "test";
		String password = "franek";
		Assert.assertEquals(0, Auth.register(login, password, "ala"));
	}
	public void test5(){
		String login = "";
		String password = "";
		Assert.assertEquals(1, Auth.register(login, password, "ala"));
	}
	public void test6(){
		String login = "test";
		String password = "";
		Assert.assertEquals(1, Auth.register(login, password,""));
	}
	public void test7(){
		String login = "";
		String password = "test";
		Assert.assertEquals(1, Auth.register(login, password,""));
	}
	public void test8(){
		String login = "";
		String password = "franek";
		Assert.assertEquals("1", Auth.login(login, password));
	}
	public void test9(){
		String login = "test";
		String password = "";
		Assert.assertEquals("1", Auth.login(login, password));
	}


}
