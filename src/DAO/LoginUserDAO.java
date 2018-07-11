package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import model.LoginUser;
public class LoginUserDAO {
	private Connection con = null; 	// コネクションオブジェクト
	private Statement stmt = null; 	// ステートメントオブジェクト
	private ConnectionManager cm; // コネクションマネージャー

	// Connectionの取得
	private void getConnection() throws DAOException{
		if ( this.con != null ){ return; 	}
		cm = ConnectionManager.getInstance();
		con = cm.getConnection(); // データベースへの接続の取得
	}

	// Statementの取得
	private void createStmt() throws DAOException{
		if ( this.stmt != null){ 	return; }
		try {
			stmt =con.createStatement();
		} catch (SQLException e) {  // SQLに関する例外処理
			throw new DAOException("[createStmt]異常", e);
		}
	}

	// データを追加
	public int insertUser(LoginUser user) throws DAOException {
		getConnection();
		int count = 0;
		String sql = "INSERT INTO login_user (login_id,name, password) VALUES(?, ?, ?)";
		int id = Integer.parseInt(user.getLogin_Id());
		String name = user.getName();
		String pass = user.getPass();

		try(PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, pass);
			count += pstmt.executeUpdate();
		} catch(SQLException e) {
			throw new DAOException("[UserDAO#insertMember]異常", e);
		} finally {
			close();
		}
		return count;
	}

	private void close() throws DAOException {
		try {
			if (stmt != null) { stmt.close(); }
		} catch (SQLException e) {
			throw new DAOException("[closeStatement]異常", e);
		} finally {
			this.stmt = null;
			this.cm = null;
		}
	}

}
