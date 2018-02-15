package com.mail;

import thirdware.mail.SendMail;

public class JarTest {
	
    public static void main(String[] args) {
    	try {
			SendMail.send("C:/Email/Content.txt", "C:/Email/mailID.txt", "B@yya123!@#");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
