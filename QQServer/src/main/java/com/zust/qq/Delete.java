package com.zust.qq;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.zust.qq.entity.Friends;

public class Delete {
	int id;
	int userId;
	int deleteId;
	String userlist;
	
	public Delete(int userId,int deleteId){
		this.userId=userId;
		this.deleteId=deleteId;
	}
	public void checkdelete(){
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		String hql="FROM Friends WHERE user='"+userId+"'";
		Query query=session.createQuery(hql);
		List<Friends> list=query.list();
		if(list.size()>0){
			userlist=list.get(0).getUserList();
			this.id=list.get(0).getId();
			System.out.println("好友列表"+userlist);
			int uleng=userlist.length();
			int begin=userlist.indexOf(deleteId+"");
			int chek=userlist.indexOf(";");
			int leg=String.valueOf(deleteId).length();
			System.out.println("begin:"+begin+"chek:" +chek+"leg"+leg);
			if(begin<0){
				System.out.println("错误："+deleteId);
			}
			else if(begin==0) {
				if(chek>0){
					Friends friends2=new Friends();
				userlist=userlist.substring(begin+2);
				System.out.println(userlist);
				friends2.setId(id);
				friends2.setUserId(userId);
				friends2.setUserList(userlist);
				session.merge(friends2);
				session.getTransaction().commit();
				
			}else if(chek<0){
				System.out.println("记录1");
				System.out.println("记录1 id"+id);
				Friends friends2=(Friends)session.load(Friends.class, id); 
				session.delete(friends2);
				session.getTransaction().commit();
			}
			}
			else if(begin>0){
				System.out.println(userlist);
				Friends friends2=new Friends();
				String u1=userlist.substring(0,begin-1);	
				String u2=userlist.substring(begin+leg,uleng)+"";
				System.out.println("u1"+u1);
				System.out.println("u2"+u2);
				userlist=u1+u2;
				System.out.println("userlist"+userlist);
				friends2.setId(id);
				friends2.setUserId(userId);
				friends2.setUserList(userlist);
				session.merge(friends2);
				session.getTransaction().commit();		
			}
	}	else if(list.size()==0||list==null){
		System.out.println("没有记录");	
	}
		if (session.isOpen()) {
			session.close();
		}
	}
}
	
	
	

