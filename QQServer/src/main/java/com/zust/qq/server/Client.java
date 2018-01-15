package com.zust.qq.server;

import java.net.Socket;

public class Client {
	boolean flag=false;
	Socket s =null;
	public Client(boolean flag, Socket s) {
		super();
		this.flag = flag;
		this.s = s;
	}
	public void printX(){
		System.out.println(s.getInetAddress()+" "+s.getPort());
	}
}
