package com.zust.qq;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class AddFriends {
	private String userId;
	private String fId;
	public AddFriends(String userId, String fId) {
		this.userId=userId;
		this.fId=fId;
	}
	
	public void add(String userId, String fId){
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		
		String hql = "SELECT userList FROM Friends WHERE user='"+userId+"'";
		Query query = session.createQuery(hql);
		String list=query.uniqueResult().toString();
		System.out.println(list);
		list=list+";"+fId;
		
		
		String hql2 = "UPDATE Friends SET userList='"+list+"' WHERE user='"+userId+"' ";
		Query query2 = session.createQuery(hql2);
		int ret=query2.executeUpdate();
		session.getTransaction().commit();

		if (session.isOpen()) {
			session.close();
		}
	}
		public void update(String userId, String fId){
			Configuration cfg = new Configuration().configure();
			SessionFactory factory = cfg.buildSessionFactory();
			Session session = factory.openSession();
			session.beginTransaction();

			String hql = "DELETE FROM Invite  WHERE user2='"+userId+"' AND user1='"+fId+"'";
			Query query = session.createQuery(hql);
			int ret=query.executeUpdate();
			session.getTransaction().commit();

			if (session.isOpen()) {
				session.close();
			}
	}

	public  String getNick(String fId){
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();

		String hql = "SELECT nickname FROM User WHERE id=" + fId;
		Query query = session.createQuery(hql);
		String nickname = query.uniqueResult().toString();
		if (session.isOpen()) {
			session.close();
		}
		return  nickname;
	}
	public String getFriends(String userId) {
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		String hql = "SELECT userList FROM Friends WHERE user='" + userId + "'";
		Query query = session.createQuery(hql);
		String userList = query.uniqueResult().toString();
		String[] ary = userList.split(";");
		String nicknamelist = "";
		for (int i = 0; i < ary.length; i++) {
			int id1 = Integer.valueOf(ary[i]).intValue();
			String hql2 = "SELECT nickname FROM User WHERE id=" + id1;
			Query query2 = session.createQuery(hql2);
			String nickname = query2.uniqueResult().toString();
			nicknamelist = nicknamelist + nickname + ";";
		}
		userList = userList + "&" + nicknamelist;
		if (session.isOpen()) {
			session.close();
		}
		return userList;
	}
}
