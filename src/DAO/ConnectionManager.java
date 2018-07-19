package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	// URL・ユーザ名・パスワードの設定
	private final static String URL = "jdbc:mysql://localhost:3306/JSProjectDB?useSSH=false&characterEncoding=UTF-8&serverTimezone=JST";
	//private final static String URL = "dbc:sqlserver://127.0.0.1:1433;DatabaseName=JSProjectDB";
	private final static String USER = "junichi";
	private final static String PASSWORD = "_junichi_";
	// コネクションオブジェクト
	private Connection connection = null;

	 // このクラスに唯一のインスタンス
	private static ConnectionManager instance = new ConnectionManager();

	/*
	 * static初期化子
	 */
	static {
		// JDBCドライバのロード
		String drv = "com.mysql.jdbc.Driver";
		//String drv = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		try {
			Class.forName(drv);
		} catch (ClassNotFoundException e) {
			System.out.println("ドライバがありません" + e.getMessage());
		}
	}

	/**
	 * コンストラクタ
	 */
	private ConnectionManager() {	}
	/*
	 * インスタンス取得メソッド
	 */
	public static ConnectionManager getInstance() { return instance; }

	/**
	 * DBの接続	 *
	 * @return コネクション
	 * @throws Exception
	 */
	public synchronized Connection getConnection() throws DAOException {
		// 	コネクションの確立
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			connection = null;
			throw new DAOException("[conect]異常", e);github
		}
		return connection;
	}

	/**
	 * DBの切断
	 */
	public void closeConnection() throws DAOException{
		try {
			if (connection != null) { 	connection.close(); }
		} catch (SQLException e) {
			throw new DAOException("[closeConnection]異常", e);
		} finally {
			connection = null;
		}
	}
}