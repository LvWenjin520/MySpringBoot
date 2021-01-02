package org.lwj.MySpringBoot.login.entity;

/**
 * 用户的实体类
 * @author LvWenJin
 *
 */
public class User {
	private int id;
	private String userName;
	private String passWord;
	private String roles;
	
	
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	public User(int id, String userName, String passWord, String roles) {
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.roles = roles;
	}
	public User() {
	}
	
	
	
	
}
