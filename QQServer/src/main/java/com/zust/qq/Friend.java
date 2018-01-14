package com.zust.qq;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.zust.qq.entity.*;
public class Friend {
	private String serchmessage;
	

	
	public Friend(String serchmessage) {
		super();
		this.serchmessage = serchmessage;
	}



	public  String  findUser(){
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		String hql="FROM User u WHERE u.name='"+serchmessage+"'"+" or u.nickname='"+serchmessage+"'";
		Query query=session.createQuery(hql);
	    List<User> user=query.list();
		session.getTransaction().commit();
		String userlist ="";
		for(int i=0;i<user.size();i++){

			if(i==0)
				userlist=user.get(i).getName();
			else
			 userlist=userlist+";"+user.get(i).getName();
		}
		

		if (session.isOpen()) {
			session.close();
		}
		return userlist;
	}
	
}
