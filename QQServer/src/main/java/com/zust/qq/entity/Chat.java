package com.zust.qq.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chat")
public class Chat {
	@Id
	@Column(name = "id", nullable = false, length = 36)
	private int id;
	@Column(name="user1")	private int user1;
	@Column(name ="user2")	private int user2;
	@Column(name = "s1")	private String s1;
	@Column(name = "s2"	)	private String s2;
	@Column(name = "ds1")	private String ds1;
	@Column(name = "ds2")	private String ds2;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser1() {
		return user1;
	}
	public void setUser1(int user1) {
		this.user1 = user1;
	}
	public int getUser2() {
		return user2;
	}
	public void setUser2(int user2) {
		this.user2 = user2;
	}
	public String getS1() {
		return s1;
	}
	public void setS1(String s1) {
		this.s1 = s1;
	}
	public String getS2() {
		return s2;
	}
	public void setS2(String s2) {
		this.s2 = s2;
	}
	public String getDs1() {
		return ds1;
	}
	public void setDs1(String ds1) {
		this.ds1 = ds1;
	}
	public String getDs2() {
		return ds2;
	}
	public void setDs2(String ds2) {
		this.ds2 = ds2;
	}
		
		
}
