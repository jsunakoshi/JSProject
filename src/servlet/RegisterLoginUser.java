package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.LoginUserDAO;
import model.LoginUser;

/**
 * Servlet implementation class RegisterLoginUser
 */
@WebServlet("/RegisterLoginUser")
public class RegisterLoginUser extends HttpServlet {
	LoginUserDAO memberDao = new LoginUserDAO();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterLoginUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//フォワード先
		String forwardPath = null;

		//サーブレットクラスの動作を決定する「action」の値を
		//リクエストパラメータから取得
		String action = request.getParameter("action");

		//「登録の開始」をリクエストされたときの処理
		if(action == null){
			//フォワード先を設定
			forwardPath = "/WEB-INF/jsp/RegisterForm.jsp";
		}
		//登録確認画面から「登録実行」をリクエストされたときの処理
		else if(action.equals("done")){
			//セッションスコープに保存された登録ユーザを
			HttpSession session = request.getSession();
			LoginUser registerUser = (LoginUser)session.getAttribute("registerUser");
			System.out.println("Start servlet.RegisterLoginUser.doGet");
			System.out.println("ID="+registerUser.getLogin_Id());
			System.out.println("PW="+registerUser.getPass());
			System.out.println("Name="+registerUser.getName());

			//登録処理の呼び出し
			//RegisterLoginUser logic = new RegisterLoginUser();
			//logic.exute(registerUser);
			boolean bsuccess=registerUser.insert();
			//不要となったセッションスコープ内のインスタンスを削除
			session.removeAttribute("registerUser");
			if(bsuccess)
				//登録後のフォワード先を設定
				forwardPath = "/WEB-INF/jsp/registerDone.jsp";
			else
				forwardPath = "/WEB-INF/jsp/registerNot.jsp";

		}

		// 設定されたフォワード先を設定
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");

		//登録するユーザの情報を設定
		LoginUser registerUser = new LoginUser(id, name, pass);

		//セッションスコープに登録ユーザを保存
		HttpSession session = request.getSession();
		session.setAttribute("registerUser", registerUser);

		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registerConfirm.jsp");
		dispatcher.forward(request, response);
	}
/*
	public boolean execute(LoginUser user){
		System.out.println("Start servlet.RegisterLoginUser execute");
		System.out.println("ID="+user.getLogin_Id());
		System.out.println("PW="+user.getPass());
		System.out.println("Name="+user.getName());
		boolean registCheck = user.insert();
		//登録処理
		//try {
			;
			registCheck = ;
		} catch (DAOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		System.out.println("End execute");
		return registCheck;
	}
*/
}
