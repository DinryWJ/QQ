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
import java.util.Map.Entry;

import javax.naming.directory.SearchControls;



import com.zust.qq.AddFriends;
import com.zust.qq.Delete;
import com.zust.qq.Friend;
import com.zust.qq.Inviate;
import com.zust.qq.Invite;
import com.zust.qq.Login;
import com.zust.qq.Quit;
import com.zust.qq.Regist;

public class ServerSocketThread extends Thread {

    private Socket socket;
    private Map<String, Socket> map;
    

    public ServerSocketThread(Socket socket, Map<String, Socket> map) {
        this.socket = socket;
        this.map = map;
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
                    Login login = new Login(Lname,Lpassword);
                    flag = login.checkLogin();
                    String Lnickname=login.getnickname();                
                    os = socket.getOutputStream();
                    pw = new PrintWriter(os);
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
                    	pw.println("1");
                    	inviate.addinviate();
                    }else
                       	pw.println("0");
                    pw.flush();
 
                    break;
                    
                case 'M':
                	System.out.println(socket.getInetAddress()+" "+socket.getPort());
                	String[] ss = message.split("&");
                	System.out.println(ss[1]);
                	System.out.println(ss[2]);
                	System.out.println(ss[3]);
                	System.out.println(ss[4]);
                	
                	System.out.println(map.size());
                	for (Entry<String, Socket> entry : map.entrySet()) {
                        //Map.entry<Integer,String> 映射项（键-值对）  有几个方法：用上面的名字entry
                        //entry.getKey() ;entry.getValue(); entry.setValue();
                        //map.entrySet()  返回此映射中包含的映射关系的 Set视图。
                        System.out.println("key= " + entry.getKey() + " and value= "
                                + entry.getValue());
                    }
            		if(map.get(ss[2]) != null){
            			System.out.println(map.get(ss[2]).getInetAddress()+" "+map.get(ss[2]).getPort());
            			os = map.get(ss[2]).getOutputStream();
            			pw = new PrintWriter(os);
            			pw.println(message);
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
                  	System.out.println(socket.getInetAddress()+" "+socket.getPort());
                  	String userIds = "";             	
                   	String IuserId = message.substring(2);                 	                                                                     
                      Invite invite = new Invite(IuserId);
                      List list1=invite.getInvite();
                      for(int i =0;i<list1.size();i++)  {           
                      userIds=userIds+list1.get(i)+"&";
                   
                      }
                  	
                      os = socket.getOutputStream();
                       pw = new PrintWriter(os);
                       pw.println("1&"+userIds);
                       pw.flush();               
                      break;

                case 'C':
                	System.out.println(socket.getInetAddress()+" "+socket.getPort());
                	String[] sp = message.split("&");
                	int id1 = Integer.parseInt(sp[1]);
                	int id2 = Integer.parseInt(sp[2]);
                	new Delete(id1,id2).checkdelete();
                	new Delete(id2,id1).checkdelete();
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
                    	pw.println("1&"+serchinf);
                    }   	
                    else{
                    	pw.println("0");
                    }
                    	
                    pw.flush();
  
                	
                    break;
                case 'Y':
            		System.out.println("添加好友"+message);
                  	System.out.println(socket.getInetAddress()+" "+socket.getPort());
                  	String[] Ids=message.split("&");       	
                   	String UserId = Ids[1];   
                   	String fId = Ids[2];  
                   	AddFriends  addFriends = new AddFriends(UserId,fId);
//                    String newnick=addFriends.getNick(fId);
                   	addFriends.add(UserId, fId);
                    addFriends.update(UserId, fId);
                    String fList=addFriends.getFriends(UserId);
                    String[] friendslist1=fList.split("&");
                    String newfids=friendslist1[0];
                    String newfnicknames=friendslist1[1];
                      os = socket.getOutputStream();
                       pw = new PrintWriter(os);
                    pw.println(UserId+"&"+Ids[4]+"&"+Ids[5]+"&"+Ids[6]
                            +"&"+newfids+"&"+newfnicknames);
                       pw.flush();               
                      break;
                case 'N':
                        System.out.println("删除请求"+message);
                        System.out.println(socket.getInetAddress()+" "+socket.getPort());
                        String[] Ids1=message.split("&");
                        String UserId1 = Ids1[1];
                        String fId1 = Ids1[2];

                        AddFriends  addFriends1 = new AddFriends(UserId1,fId1);
                        addFriends1.update(UserId1, fId1);

                        os = socket.getOutputStream();
                        pw = new PrintWriter(os);
                        //pw.println("G&"+UserId1+"&"+oldperson[1]+"&"+oldperson[2]+"&"+oldperson[3]+"&"+friendsname+"&"+friendsnickname);
                        pw.flush();
                        break;

                case 'Q':
                	System.out.println(socket.getInetAddress()+" "+socket.getPort());
                	int indexQ1 = message.indexOf("&");
                	String Qid = message.substring(indexQ1+1);
                	System.out.println(Qid);
                	flag = new Quit(Qid).checkQuit();
                	if(map.get(Qid)!=null){
                		map.remove(Qid);
                	}	
                           
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
                    String[] friendslist={};
                    String friendsname="";
                    String friendsnickname="";
                    System.out.println(("测试"+friends));
                    if (friends.length()>1) {
                        friendslist = friends.split("&");
                        friendsname = friendslist[0];
                        friendsnickname = friendslist[1];
                    }
                    os = socket.getOutputStream();
                    pw = new PrintWriter(os);
                    map.put(userId+"", socket);
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