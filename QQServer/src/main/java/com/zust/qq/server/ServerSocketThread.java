package com.zust.qq.server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.directory.SearchControls;

import com.zust.qq.Friend;
import com.zust.qq.Inviate;
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
        int xulie=0;
    	Map<String,Client> map = new HashMap<String,Client>();
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
            String message;
            while ((message = br.readLine()) != null) {
            	key=message.charAt(0);System.out.println("Thread");
            	System.out.println(key);
            	switch(key){
            	case 'L':
            		System.out.println(socket.getInetAddress()+" "+socket.getPort());
            		System.out.println("客户端登录");
                	int indexL1= message.indexOf("&");
                	int indexL2 = message.lastIndexOf("&");               	
                    String Lname = message.substring(indexL1+1,indexL2);
                    String Lpassword = message.substring(indexL2+1);
                   // System.out.println(Lname);
                    //System.out.println(Lpassword);
                    Login login = new Login(Lname,Lpassword);
                    flag = login.checkLogin();
                    String Lnickname=login.getnickname();                
                       
                    os = socket.getOutputStream();
                    pw = new PrintWriter(os);
//                    xulie++;
//            		map.put(Lname,new Client(xulie,socket));
            		flag=true;
                    if(flag){
                    	System.out.println("1&user&"+Lname+"&"+Lpassword+"&"+Lnickname);
                    	pw.println("1&user&"+Lname+"&"+Lpassword+"&"+Lnickname);
                    }
                    else
                    	pw.println("0");
                    pw.flush();
                               
                    break;

               
                case 'A':
                	System.out.println(socket.getInetAddress()+" "+socket.getPort());
                	int add1=message.indexOf("&");
                	int add2 = message.lastIndexOf("&");  
                    String username = message.substring(add1+1,add2);
                    String addusername = message.substring(add2+1);
                    System.out.println(username+"\t"+addusername);
                    Inviate inviate=new Inviate(username,addusername);
                     boolean falg2= inviate.check();          
                     os = socket.getOutputStream();
                     pw = new PrintWriter(os);
                    if(falg2){
                    	pw.write("1");
                    	inviate.addinviate();
                    }else
                       	pw.write("0");
                    pw.flush();
                    socket.shutdownOutput();  
                    break;
                    
                case 'M':
                	System.out.println(socket.getInetAddress()+" "+socket.getPort());
                	String[] ss = message.split("&");
                	System.out.println(ss[1]);
                	System.out.println(ss[2]);
                	System.out.println(ss[3]);
                	System.out.println(ss[4]);
                	
                	
            		if(map.get(ss[2]) != null){
            			os = map.get(ss[2]).s.getOutputStream();
            			pw = new PrintWriter(os);
            			pw.write(message);
            			pw.flush();
            		}
                	
                	        
//                    os = socket.getOutputStream();
//                    pw = new PrintWriter(os);
//                    flag=true;
//                    if(flag)
//                    	pw.write("1");
//                    else
//                    	pw.write("0");
//                    pw.flush();
                           
                    break;
                case 'D':
                    System.out.println("d");
                    break;
                case 'S':
                	System.out.println(socket.getInetAddress()+" "+socket.getPort());
                	int serch1= message.indexOf("&");
                	String serchmessage=message.substring(serch1+1).trim();
                	String serchinf=""+new Friend(serchmessage).findUser();//搜索信息
                	if(serchinf.length()>0)flag=true;
                    os = socket.getOutputStream();
                    pw = new PrintWriter(os);                   
                    if(flag){
                    	pw.write("1&"+serchinf);
                    }   	
                    else{
                    	pw.write("0");
                    }
                    	
                    pw.flush();
                    socket.shutdownOutput();   
                	
                    break;

                case 'Q':
                	System.out.println(socket.getInetAddress()+" "+socket.getPort());
                	int indexQ1 = message.indexOf("&");
                	String Qname = message.substring(indexQ1+1);
                	System.out.println(Qname);
                	flag = new Quit(Qname).checkQuit();        
                    os = socket.getOutputStream();
                    pw = new PrintWriter(os);
                    if(flag)
                    	pw.println("1");
                    else
                    	pw.println("0");
                    pw.flush();                             
                    break;
                case 'R':
                	System.out.println(socket.getInetAddress()+" "+socket.getPort());
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
                    	pw.println("1");
                    	}
                    else
                    	pw.println("0");
                    pw.flush();
                    socket.shutdownOutput();   
                    break;
                case 'G':
                	System.out.println(socket.getInetAddress()+" "+socket.getPort());
                	int indexG1= message.indexOf("&");
                	int indexG2 = message.lastIndexOf("&");               	
                    String Gname = message.substring(indexG1+1,indexG2);
                    String Gpassword = message.substring(indexG2+1);         
                    Login login1 = new Login(Gname,Gpassword);
                    String Gnickname=login1.getnickname();
                    int userId=login1.getId();
                    String friends=login1.getFriends();//好友id列表
                    String[] friendslist=friends.split("&");
                    String friendsname=friendslist[0];
                    String friendsnickname=friendslist[1];        
                    os = socket.getOutputStream();
                    pw = new PrintWriter(os);
                    pw.println("G&"+userId+"&"+Gname+"&"+Gpassword+"&"+Gnickname+"&"+friendsname+"&"+friendsnickname);                  
                    pw.flush();                
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