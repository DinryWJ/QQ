package com.zust.qq.server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import javax.naming.directory.SearchControls;

import com.zust.qq.Friend;
import com.zust.qq.Login;
import com.zust.qq.Quit;
import com.zust.qq.Regist;

public class ServerSocketThread extends Thread {

    private Socket socket;

    public ServerSocketThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
    	char key;
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        OutputStream os = null;
        PrintWriter pw = null;
		boolean flag=false;
        try {
          
            is = socket.getInputStream();
         
            isr = new InputStreamReader(is);
         
            br = new BufferedReader(isr);
            String message = null;
            while ((message = br.readLine()) != null) {
            	key=message.charAt(0);
            	System.out.println(key);
            	switch(key){
            	case 'L':
                	int indexL1= message.indexOf("&");
                	int indexL2 = message.lastIndexOf("&");               	
                    String Lname = message.substring(indexL1+1,indexL2);
                    String Lpassword = message.substring(indexL2+1);
                    System.out.println(Lname);
                    System.out.println(Lpassword);
                    Login login = new Login(Lname,Lpassword);
                    flag = login.checkLogin();
                    String Lnickname=login.getnickname();
                    String friends=login.getFriends();//好友id列表
                    String[] friendslist=friends.split("&");
                    String friendsname=friendslist[0];
                    String friendsnickname=friendslist[1];
                    socket.shutdownInput();            
                    os = socket.getOutputStream();
                    pw = new PrintWriter(os);
                    //System.out.println("1&user&"+Lname+"&"+Lpassword+"&"+Lnickname+"&"+friendsname+"&"+friendsnickname);
                    //friendsnickname  name1;name2;
                    if(flag)
                    	pw.write("1&user&"+Lname+"&"+Lpassword+"&"+Lnickname+"&"+friendsname+"&"+friendsnickname);
                    else
                    	pw.write("0");
                    pw.flush();
                    socket.shutdownOutput();                  
                    break;
                case 'A':
                    System.out.println('b');
                    break;
                case 'M':
                    System.out.println('c');
                    break;
                case 'D':
                    System.out.println("d");
                    break;
                case 'S':
                	int serch1= message.indexOf("&");
                	String serchmessage=message.substring(serch1+1).trim();
                	String serchinf=""+new Friend(serchmessage).findUser();//搜索信息
                	System.out.println("test1;"+serchinf);
                    socket.shutdownInput();            
                    os = socket.getOutputStream();
                    pw = new PrintWriter(os);                   
                    if(flag){
                    	pw.write("0");
                    	System.out.println("test2");
                    }
                    	
                    else{
                    	System.out.println("test3");
                    	pw.write("1&"+serchinf);
                    }
                    	
                    pw.flush();
                    socket.shutdownOutput();   
                	
                    break;

                case 'Q':
                	int indexQ1 = message.indexOf("&");
                	String Qname = message.substring(indexQ1+1);
                	System.out.println(Qname);
                	flag = new Quit(Qname).checkQuit();
                	socket.shutdownInput();            
                    os = socket.getOutputStream();
                    pw = new PrintWriter(os);
                    if(flag)
                    	pw.write("1");
                    else
                    	pw.write("0");
                    pw.flush();
                    socket.shutdownOutput();             
                    break;
                case 'R':
                	int indexR1= message.indexOf("&");
                	int indexR2 = message.indexOf("&",indexR1+1);
                	int indexR3 = message.lastIndexOf("&");
                	String Rname = message.substring(indexR1+1,indexR2);
                    String Rpassword = message.substring(indexR2+1,indexR3);
                    String Rnickname = message.substring(indexR3+1);
                    System.out.println("Rname:"+Rname+" Rpassword:"+Rpassword+" Rnickname:"+Rnickname);
                    Regist regist=new Regist(Rname, Rpassword, Rnickname);
                    flag=regist.checkRegist();
                    socket.shutdownInput();            
                    os = socket.getOutputStream();
                    pw = new PrintWriter(os);
                    if(flag)
                    	{
                    	regist.registnew();
                    	pw.write("1");
                    	}
                    else
                    	pw.write("0");
                    pw.flush();
                    socket.shutdownOutput();   
                    break;
                default:
                    System.out.println("错误");
                    break;
            	}
            }
          
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (pw != null) {
                pw.close();
            }
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (isr != null) {
                    isr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (isr != null) {
                    isr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}