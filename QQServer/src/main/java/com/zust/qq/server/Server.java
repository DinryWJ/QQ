package com.zust.qq.server;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class Server {

	private static ServerSocket SERVER_SOCKET = null;;
	
	private static Map<String,Socket> map = new HashMap<String,Socket>();
	
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
				//System.out.println(socket.getInetAddress()+" "+socket.getPort());
				new ServerSocketThread(socket,map).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}