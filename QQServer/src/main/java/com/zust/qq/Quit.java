package com.zust.qq;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.zust.qq.entity.User;

public class Quit {
	private String sid;

	public Quit(String sid) {
		super();
		this.sid = sid;
	}
	
	public boolean checkQuit(){
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		int id = Integer.parseInt(sid);
		User user = (User) session.get(User.class, id);
		user.setStatus(false);
		session.getTransaction().commit();

		if (session.isOpen()) {
			session.close();
		}
		return true;
		
	}
}
