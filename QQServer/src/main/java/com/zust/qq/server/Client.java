package com.zust.qq.server;

import java.net.Socket;

public class Client {
	int ClientIndex;
	Socket s =null;
	public Client(int clientIndex, Socket s) {
		super();
		ClientIndex = clientIndex;
		this.s = s;
	}
	public void printX(){
		System.out.println(s.getInetAddress()+" "+s.getPort());
	}
}
