package com.zust.qq;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.zust.qq.entity.Chat;
import com.zust.qq.entity.Friends;
import com.zust.qq.entity.User;

/**
 * 
 *
 */
public class Test1 {
	public static void main(String[] args) {
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();

//		User user = new User();
//		user.setName("admin2");
//		user.setPassword("123");
//		user.setNickname("user2");
//		session.save(user);
		
//		Chat c = new Chat();
//		c.setUser1(1);
//		c.setUser2(2);
//		session.save(c);
		Friends f= new Friends();
		f.setUserId(1);
		session.save(f);
		session.getTransaction().commit();

		if (session.isOpen()) {
			session.close();
		}
	}
}
