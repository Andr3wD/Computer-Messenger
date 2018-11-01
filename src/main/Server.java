package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
	//this class will be for creating servers
	ServerSocket server;
	Socket client;
	boolean close = false;
	int port;
	//constructor
	Server(int port){
		this.port = port;
	}
	//run method is part of Runnable, it's what's called when the thread is started.
	public void run() {
		try {
			server = new ServerSocket(port); //0 port automatically assigns the port
			System.out.println("Waiting for connction");
			client = server.accept(); //waits for connection on port, stops all activity until connection is established
			System.out.println("Client has connected, starting to listen to socket.");
			listen();
		} catch (IOException e) {
			System.out.println("Failed connection.");
			System.exit(-1);
		}
	}
	public void listen() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			//BufferedReader parses through the text, InputStreamReader turns the bytes into strings, .getInputStream() gets the stream for messages
			PrintWriter out = new PrintWriter(client.getOutputStream(),true);
			//PrintWriter prints formatted representations of objects to a text-output stream.
			while(true) {
				try {
					String input = in.readLine(); //read from input stream
					System.out.println("Server in:" + input);
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