package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServerManager implements Runnable {
	//this class manages the server connections to multiple clients
	
	int port;
	boolean close = false;
	public HashMap<Integer, Server> serverList = new HashMap<>();
	
	public void run() {
		openConnection();
	}
	
	/**
	 * Opens connection for any client, opens a new reader for each individual client.
	 */
	public void openConnection() {
		while(!close) {
			try {
				ServerSocket server = new ServerSocket(port); //0 port automatically assigns the port
				System.out.println("Waiting for connction");
				Socket client = server.accept(); //waits for connection on port, stops all activity until connection is established
				System.out.println("Client has connected, starting to listen to socket.");
				Server s = new Server(server,client); //Creates new server with given int port. 0 = picks port automatically
				Thread t = new Thread(s); //establishes a new thread with given object
				t.start(); //starts that thread
				serverList.put(serverList.size(), s); //add client connection to list.
			} catch (IOException e) {
				//System.out.println("No connection.");
				//System.exit(-1);
			}
			try {
				Thread.sleep(1000); //stop thread for a moment to prevent resource hog
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("Failed sleep");
				System.exit(-1);
			}
		}
	}
	
	/**
	 * Closes all currently running connections to clients.
	 */
	public void closeAllServers() {
		for(Server v:serverList.values()) {
			v.close();
		}
	}
}
