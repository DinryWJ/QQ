package com.zust.qq;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.zust.qq.entity.User;

public class Quit {
	private String name;

	public Quit(String name) {
		super();
		this.name = name;
	}
	
	public boolean checkQuit(){
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		
		String hql = "FROM User WHERE name='"+name+"'";
		User user = (User) session.createQuery(hql).uniqueResult();
		user.setStatus(false);
		session.getTransaction().commit();

		if (session.isOpen()) {
			session.close();
		}
		return true;
		
	}
}
