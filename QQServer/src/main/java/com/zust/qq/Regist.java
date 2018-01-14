package com.zust.qq;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.zust.qq.entity.User;

/**
 * @author Administrator
 *
 */
public class Regist {
	private String name;
	private String password;
	private String nickname;

	public Regist(String name,String password,String nickname){
		this.name=name;
		this.password=password;
		this.nickname=nickname;
	}
	public boolean checkRegist(){
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		String hql="SELECT count(*) FROM User where name='"+name+"'";
		Query query = session.createQuery(hql);  
		int check = ((Number)query.uniqueResult()).intValue();  
		session.getTransaction().commit();
		if (session.isOpen()) {
			session.close();
		}
		if(check>0)
			return false;
		return true;
	}
	public void registnew(){
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		System.out.println("regist");
		User user=new User();
		user.setName(name);
		user.setNickname(nickname);
		user.setPassword(password);
		session.save(user);
		session.getTransaction().commit();
		if (session.isOpen()) {
			session.close();
		}
	}
	
	
}
