package com.zust.qq;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.zust.qq.entity.Invite;
import com.zust.qq.entity.User;

public class Inviate {
	private int userId;
	private int addusernameId;
	
	public Inviate(String username,String addusername){
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		String hql = "FROM User WHERE name='"+username+"'";
		User user=(User) session.createQuery(hql).uniqueResult();
		this.userId=user.getId();
		String hql2 = "FROM User WHERE name='"+addusername+"'";
		User user2=(User) session.createQuery(hql2).uniqueResult();
		this.addusernameId=user2.getId();
		session.getTransaction().commit();

		if (session.isOpen()) {
			session.close();
		}
	}
	public boolean check(){
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		String hql = "FROM Invite WHERE user1='" + userId + "' AND user2='" + addusernameId + "'";
		Query query = session.createQuery(hql);
		
		int result=query.list().size();
		session.getTransaction().commit();

		if (session.isOpen()) {
			session.close();
		}
		if(result>0)
			return false;

		return true;
	}
	public void addinviate() {
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		Invite invite=new Invite();
		System.out.println(userId+" "+addusernameId);
		invite.setUser1(userId);
		invite.setUser2(addusernameId);
		SimpleDateFormat sd = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
		invite.setTime(sd.format(new Date()));
		session.save(invite);
		session.getTransaction().commit();

		if (session.isOpen()) {
			session.close();
		}
	}
}
