package model;


import DAO.DAOException;
import DAO.LoginUserDAO;

public class RegisterLoginUser {
	LoginUserDAO memberDao = new LoginUserDAO();

	public boolean exeute(LoginUser user){
		System.out.println("model.RegisterLoginUser execute");
		boolean registCheck = false;

		//登録処理
		try {
			memberDao.insertUser(user);
			registCheck = true;
		} catch (DAOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return registCheck;
	}

}
