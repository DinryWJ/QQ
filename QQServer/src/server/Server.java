package server;

import java.io.*;
import java.net.*;

public class Server {

	private static ServerSocket SERVER_SOCKET = null;;

	static {
		try {
			SERVER_SOCKET = new ServerSocket(8888);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			System.out.println("******wait for connect*****");
			Socket socket = null;
			while (true) {
				
				socket = SERVER_SOCKET.accept();
				
				new ServerSocketThread(socket).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}