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
		String login = "admin";
		String password = "franek";
		Assert.assertEquals(3, Auth.login(login, password));
	}

	public void test2(){
		String login = "admin";
		String password = "franek";
		Assert.assertEquals(2, Auth.login(login, password));
	}
	
	public void test3(){
		String login = "admin";
		String password = "franek";
		Assert.assertEquals(1, Auth.login(login, password));
	}
	
	public void test4(){
		String login = "admin";
		String password = "franek";
		Assert.assertEquals(0, Auth.register(login, password, "ala"));
	}

}
