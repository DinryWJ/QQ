package com.zust.qq;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.zust.qq.entity.Friends;
import com.zust.qq.entity.User;

public class Login {
	private String name;
	private String password;

	public Login(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public boolean checkLogin() {
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();

		String hql = "SELECT count(*) FROM User WHERE name='" + name + "' AND password='" + password + "'";
		Query query = session.createQuery(hql);
		int result = ((Number) query.uniqueResult()).intValue();
		System.out.println(result);
		session.getTransaction().commit();

		if (session.isOpen()) {
			session.close();
		}

		if (result > 0) {
			
			if(setSign(name)){
				return true;
			}else 
				return false;
			
		}
		return false;

	}

	private boolean setSign(String name2) {
		// TODO Auto-generated method stub
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		String hql = "FROM User WHERE name='" + name + "'";
		User user = (User) session.createQuery(hql).uniqueResult();
		if(user.isStatus()==true){
			return false;
		}else
			user.setStatus(true);
		session.getTransaction().commit();

		if (session.isOpen()) {
			session.close();
		}
		return true;
	}

	public String getnickname() {
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		String hql = "SELECT nickname FROM User WHERE name='" + name + "'";
		Query query = session.createQuery(hql);
		String nickname = query.uniqueResult().toString();
		if (session.isOpen()) {
			session.close();
		}
		return nickname;
	}

	public String getFriends() {
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		String hql = "FROM User WHERE name='" + name + "'";
		User user = (User) session.createQuery(hql).uniqueResult();
		int id = user.getId();
		String[] ary={};
		String userList="";
		String hql2 = "SELECT userList FROM Friends WHERE user=" + id;
		Query query = session.createQuery(hql2);
		Friends f= (Friends)session.get(Friends.class,id);
		if (null!=f){
			 userList = query.uniqueResult().toString();
		    ary = userList.split(";");
	}
		String nicknamelist = "";
		if(ary!=null) {
			for (int i = 0; i < ary.length; i++) {
				int id1 = Integer.valueOf(ary[i]).intValue();
				String hql3 = "SELECT nickname FROM User WHERE id=" + id1;
				Query query2 = session.createQuery(hql3);
				String nickname = query2.uniqueResult().toString();
				nicknamelist = nicknamelist + nickname + ";";
			}

			userList = userList + "&" + nicknamelist;
		}
		if (session.isOpen()) {
			session.close();
		}
		return userList;
	}

	public int getId() {
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		String hql = "FROM User WHERE name='" + name + "'";
		User user = (User) session.createQuery(hql).uniqueResult();
		int id = user.getId();
		if (session.isOpen()) {
			session.close();
		}
		return id;
			
	}

}
