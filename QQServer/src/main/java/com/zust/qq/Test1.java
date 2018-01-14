package com.zust.qq;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.zust.qq.entity.Chat;
import com.zust.qq.entity.Friends;
import com.zust.qq.entity.Invite;
import com.zust.qq.entity.User;

/**
 * 
 *
 */
public class Test1 {
	public static void main(String[] args) {
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
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
//		Friends f= new Friends();
//		f.setUserId(1);
//		session.save(f);
		SimpleDateFormat sd = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");

		Invite i = new Invite();
		i.setUser1(1);
		i.setUser2(2);
		i.setTime(sd.format(new Date()));
		session.save(i);
		session.getTransaction().commit();

		if (session.isOpen()) {
			session.close();
		}
	}
}
