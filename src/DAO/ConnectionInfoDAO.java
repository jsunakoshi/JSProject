package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import model.ConnectionInfo;

public class ConnectionInfoDAO {
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
		} catch (SQLException ex) {  // SQLに関する例外処理
			throw new DAOException("[createStmt]異常", ex);
		}
	}

	public boolean insert(ConnectionInfo con) throws DAOException {
		return true;
	}

}