package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	// URL・ユーザ名・パスワードの設定
	private final static String URL = "jdbc:mysql://localhost:3306/JSProjectDB?useSSH=false&characterEncoding=UTF-8&serverTimezone=JST";
	//private final static String URL = "jdbc:mysql://localhost:3306/JSProjectDB";
	private final static String USER = "root";
	private final static String PASSWORD = "mysql";
	//private final static String URL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=JSProjectDB";
	//private final static String USER = "junichi";
	//private final static String PASSWORD = "_junichi_";
	// コネクションオブジェクト
	private Connection connection = null;

	 // このクラスに唯一のインスタンス
	private static ConnectionManager instance = new ConnectionManager();

	/*
	 * static初期化子
	 */
	static {
		System.out.println("DAO.ConnectionManager JDBCドライバのロード");
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
	public ConnectionManager() {	}
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
		System.out.println("DAO.ConnectionManager.getconnection コネクションの確立");		// 	コネクションの確立
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			connection = null;
			System.out.println("DAO.ConnectionManager.getconnection コネクションの確立失敗");// 	コネクションの確立
			System.out.println("SQLException Code="+e.getErrorCode());
			System.out.println("SQLException State="+e.getSQLState());
			System.out.println("SQLException Message="+e.getMessage());
			throw new DAOException("[conect]異常", e);
		}
		return connection;
	}

	/**
	 * DBの切断
	 */
	public void closeConnection() throws DAOException{
		try {
			if (connection != null) { 	connection.close(); }
		} catch (SQLException ex) {
			throw new DAOException("[closeConnection]異常", ex);
		} finally {
			connection = null;
		}
	}
}