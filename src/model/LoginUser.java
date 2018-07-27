package model;

import java.io.Serializable;

import DAO.DAOException;
import DAO.LoginUserDAO;

public class LoginUser implements Serializable{
	//フィールド
	private int id;
	private String login_id;
	private String name;
	private String pass;
	private String error;

	public LoginUser(){}
	public LoginUser(String id, String name, String pass){
		this.login_id = id;
		this.name = name;
		this.pass = pass;
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

	public void putError(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}
	public String getLogin_Id() {
		return login_id;
	}

	public boolean insert() {
		LoginUserDAO userDAO = new LoginUserDAO();
		try {
			userDAO.insertUser(this);
		}catch(DAOException e) {
			e.printStackTrace();
		}
		return true;

	}
}
