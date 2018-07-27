package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DAOException;
import DAO.SiteInfoDAO;
import model.SiteInfo;

/**
 * Servlet implementation class RegisterSiteInfo
 */
@WebServlet("/RegisterSiteInfo")
public class RegisterSiteInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SiteInfoDAO siteinfoDao = new SiteInfoDAO();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterSiteInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//フォワード先
		String forwardPath = null;

		//サーブレットクラスの動作を決定する「action」の値を
		//リクエストパラメータから取得
		String action = request.getParameter("action");

		//「登録の開始」をリクエストされたときの処理
		if(action == null){
			//フォワード先を設定
			forwardPath = "/WEB-INF/jsp/RegisterSiteForm.jsp";
		}
		//登録確認画面から「登録実行」をリクエストされたときの処理
		else if(action.equals("done")){
			//セッションスコープに保存された登録ユーザを
			HttpSession session = request.getSession();
			SiteInfo siteinfo = (SiteInfo)session.getAttribute("siteinfo");

			//登録処理の呼び出し
			//RegisterLoginUser logic = new RegisterLoginUser();
			//logic.exute(registerUser);
			exute(siteinfo);
			//不要となったセッションスコープ内のインスタンスを削除
			session.removeAttribute("siteinfo");

			//登録後のフォワード先を設定
			forwardPath = "/WEB-INF/jsp/registerDone.jsp";
			// 設定されたフォワード先を設定
			RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
			dispatcher.forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String site = request.getParameter("site");
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		String comment = request.getParameter("comment");

		//登録するユーザの情報を設定
		SiteInfo siteinfo = new SiteInfo(site,id, pass, email, comment);

		//セッションスコープに登録ユーザを保存
		HttpSession session = request.getSession();
		session.setAttribute("SiteInfo", siteinfo);

		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registerSiteConfirm.jsp");
		dispatcher.forward(request, response);
	}

	public boolean exute(SiteInfo siteinfo) {
		boolean registCheck = false;
		//登録処理
		try {
			siteinfoDao.insertSiteInfo(siteinfo);
			registCheck = true;
		} catch (DAOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return registCheck;
	}

}
