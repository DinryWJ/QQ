package com.zust.qq;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class Invite {

	private int userId;
	
	public Invite(String iuserId) {
		this.userId=userId;
	}
	
public List getInvite() {
		
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		 List user1 = new ArrayList();
		String hql = "SELECT user1 FROM Invite WHERE user2='" + userId + "' ORDER BY time desc";
		Query query = session.createQuery(hql);
		 user1 = query.list();
				 
        if (session.isOpen()) {
			session.close();
		}
		return user1;
	}
}
