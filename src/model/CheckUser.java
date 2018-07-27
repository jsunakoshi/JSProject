package model;

import DAO.DAOException;
import DAO.LoginUserDAO;

public class CheckUser {

	LoginUserDAO userDao = new LoginUserDAO();

	public boolean login(LoginUser user){
		boolean registCheck = false;
		//登録処理
		try {
			registCheck = userDao.checkUser(user);
			//registCheck = true;
		} catch (DAOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return registCheck;
	}

}
