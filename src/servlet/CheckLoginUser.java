package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CheckUser;
import model.LoginUser;

/**
 * Servlet implementation class CheckLoginUser
 */
@WebServlet("/CheckLoginUser")
public class CheckLoginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckLoginUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//フォワード先
		String forwardPath = null;
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");

		//ログインするユーザの情報を設定
		LoginUser loginUser = new LoginUser(id, name, pass);
		//セッションスコープに保存された登録ユーザを
		HttpSession session = request.getSession();
		session.setAttribute("LoginUser",loginUser);

		//ログインチェック
		CheckUser checkUser = new CheckUser();
		if(checkUser.login(loginUser)) {
			forwardPath = "/WEB-INF/jsp/startscreen.jsp";
		}else {
			forwardPath = "/WEB-INF/jsp/LoginError.jsp";
		}
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

}
