package main;

import java.util.Scanner;

public class Start {

	public static void main(String[] args) {
		ServerManager sman = new ServerManager();
		sman.port = 0;
		Thread t = new Thread(sman); //establishes a new thread with given object
		t.start(); //starts that thread
		Scanner scan = new Scanner(System.in);
		Client.createClient(scan,"computername",0);
	}

}
