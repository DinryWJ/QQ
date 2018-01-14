package com.zust.qq;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.zust.qq.entity.*;
public class Friend {
	//private String username;
	//private String nikename;
	private String serchmessage;
	public  String[]  findUser(String serchmessage){
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		this.serchmessage=serchmessage;
		String hql="FROM User WHERE name='"+serchmessage+"'"+"or nickname='"+serchmessage+"'";
		Query query=session.createQuery(hql);
	    List<User> user=query.list();
		session.getTransaction().commit();
		String[] userlist =new String[user.size()];
		
		for(int i=0;i<user.size();i++){
			userlist[i]=user.get(i).getName();
/*			System.out.println(user.get(i).getId()+"\t"+user.get(i).getName()+"\t"
					+user.get(i).getNickname());*/
		}
		if (session.isOpen()) {
			session.close();
		}
		return userlist;
	}
	
	
	
	
	/*public  void findUserbyusername(String username){
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		this.username=username;
		String hql="FROM User WHERE name='"+username+"'";
		Query query=session.createQuery(hql);
	    List<User> user=query.list();
		session.getTransaction().commit();
		for(int i=0;i<user.size();i++){
			System.out.println(user.get(i).getId()+"\t"+user.get(i).getName()+"\t"
					+user.get(i).getNickname());
		}
		if (session.isOpen()) {
			session.close();
		}
	}
	public  void findUserbynickname(String nikename){
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		this.nikename=nikename;
		String hql="FROM User WHERE name='"+nikename+"'";
		Query query=session.createQuery(hql);
	    List<User> user=query.list();
		session.getTransaction().commit();
		for(int i=0;i<user.size();i++){
			System.out.println(user.get(i).getId()+"\t"+user.get(i).getName()+"\t"
					+user.get(i).getNickname());
		}
		if (session.isOpen()) {
			session.close();
		}
	}*/
		
/*	public boolean addFriend(String username){
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		Friends friends=new Friends();
		String hql1="FROM Friends f WHERE f.user='"+username+"'";
		Query query=session.createQuery(hql1);
		if(query==null){
			return false;
		}
		else{
			query.get
		return true;
		}*/
		
		//friends.setUserId(userId);
		
	//}
	
/*	public List<User> findFriend(){
		
	}*/
}
