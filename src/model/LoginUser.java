package model;

import java.io.Serializable;

public class LoginUser implements Serializable{
	//フィールド
	private int id;
	private String login_id;
	private String name;
	private String pass;

	public LoginUser(){}
	public LoginUser(String id, String name, String pass){
		this.login_id = id;
		this.name = name;
		this.pass = pass;
	}

	//アクセッサ
	public String getLogin_Id() {
		return login_id;
	}

	public String getName() {
		return name;
	}

	public String getPass() {
		return pass;
	}

	public int getId() {
		return id;
	}

}
