package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends ServerManager implements Runnable {
	//this class will be for listening and managing independent client connections
	ServerSocket server;
	Socket client;
	
	public Server(ServerSocket server,Socket client) {
		this.client = client;
		this.server = server;
	}
	
	//run method is part of Runnable, it's what's called when the thread is started.
	public void run() {
		listen();
	}
	
	/**
	 * Listens for message sent by client.
	 */
	public void listen() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			//BufferedReader parses through the text, InputStreamReader turns the bytes into strings, .getInputStream() gets the stream for messages
			PrintWriter out = new PrintWriter(client.getOutputStream(),true);
			//PrintWriter prints formatted representations of objects to a text-output stream.
			while(true) {
				try {
					String input = in.readLine(); //read from input stream
					System.out.println("Server in/out:" + input);
					out.println(input); //output input to stream.
				} catch(IOException a) {
					System.out.println("Unable to read");
					System.exit(-1);
				}
			}
		} catch(IOException a) {
			System.out.println("Unable to listen on socket.");
			System.exit(-1);
		}
	}
	
	/**
	 * Closes connection with client.
	 */
	public void close() {
		try {
			close = true;
			System.out.println("Closing server...");
			server.close();
		} catch(IOException a) {
			System.out.println("Unable to close server");
			System.exit(-1);
		}
		
	}
	
}