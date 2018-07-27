package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import model.SiteInfo;

public class SiteInfoDAO {
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

	// データを追加
	public int insertSiteInfo(SiteInfo siteinfo) throws DAOException {
		getConnection();
		int count = 0;
		String sql = "INSERT INTO SiteInfo (site,id,pass,email,comment, password) VALUES(?, ?, ?, ?, ?, ?)";
		String site = siteinfo.getSite();
		String id = siteinfo.getId();
		String pass = siteinfo.getPass();
		String email = siteinfo.getEmail();
		String comment = siteinfo.getComment();

		try(PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, site);
			pstmt.setString(2, id);
			pstmt.setString(3, pass);
			pstmt.setString(4, email);
			pstmt.setString(5, comment);
			count = pstmt.executeUpdate();
		} catch(SQLException e) {
			throw new DAOException("[SiteInfoDAO#insertSite]異常", e);
		} finally {
			close();
		}
		return count;
	}

	/* データを追加
	public boolean checkUser(LoginUser user) throws DAOException {
		getConnection();
		boolean bFound = false;
		//int count = 0;
//		String name = user.getName();
		int id = Integer.parseInt(user.getLogin_Id());
		String pass = user.getPass();
		String sql = "select * from login_user where login_id=" + id;
		try(Statement stmt = con.prepareStatement(sql)) {
			ResultSet  rs = stmt.executeQuery(sql);
			if(rs.next()) {
				String pass2 = rs.getString("password");
				if(pass2.equals(pass)) {
					bFound = true;
				}
			}
		} catch(SQLException e) {
			throw new DAOException("[UserDAO#insertMember]異常", e);
		} finally {
			close();
		}
		return bFound;
	}
	*/

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
