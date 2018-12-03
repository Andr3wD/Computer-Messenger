package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	//this class will be for everything clients do
	public static void createClient(Scanner scan,String computerName,int port) {
		Socket client;
		try {
		client = new Socket(computerName,port); //connects to a socket with a given computer name.
		PrintWriter out = new PrintWriter(client.getOutputStream(),true);
		//PrintWriter prints formatted representations of objects to a text-output stream.
		BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		//BufferedReader parses through the text, InputStreamReader turns the bytes into strings, .getInputStream() gets the stream for messages
		while(true) {
			try {
				out.println(scan.nextLine()); //takes console input and prints it into the output stream.
				System.out.println("You said: " + in.readLine()); //grabs input from stream, can be used to make sure messages are being sent correctly instead of relying on client knowledge
			} catch(IOException e) {
				System.out.println("Unable to read");
				System.exit(-1);
			}
		}
		
		} catch(IOException e) {
			System.out.println("Couldn't connect client.");
			System.exit(-1);
		}
	}

}
