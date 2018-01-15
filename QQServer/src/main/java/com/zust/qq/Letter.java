package com.zust.qq;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.zust.qq.entity.Chat;

public class Letter {
	private int user1;
	private int user2;
	private String letter;
	private String time;
	public Letter(int user1, int user2, String letter, String time) {
		super();
		this.user1 = user1;
		this.user2 = user2;
		this.letter = letter;
		this.time = time;
	}
	
	public boolean sendMessage(){
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		Chat c = new Chat();
		c.setUser1(user1);
		c.setUser2(user2);
		//c.set
		session.getTransaction().commit();
		if (session.isOpen()) {
			session.close();
		}
		return false;
		
	}
}
