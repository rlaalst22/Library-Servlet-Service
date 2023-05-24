package library.servlet;

import java.io.IOException;

import java.io.PrintWriter;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import library.servlet.model.User;

public class SignInServlet extends HttpServlet {

	public SignInServlet() {
		super();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String pwd = request.getParameter("password");

		User user = null;

		ResultSet rs = null;

		PreparedStatement pstmt = null;

		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book?serverTimezone=UTC", "root",
					"1234");

			String query = "SELECT * FROM BOOK_USER WHERE email = (?);";

			pstmt = con.prepareStatement(query);

			pstmt.setString(1, email);

			// sql구문 실행하기

			rs = pstmt.executeQuery();

			while (rs.next()) {
				user = new User(rs);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException se) {

			System.out.println(se.getMessage());

		} finally {

			try {

				if (pstmt != null)
					pstmt.close();

				if (con != null)
					con.close();

			} catch (SQLException se) {

				System.out.println(se.getMessage());

			}
		}
		String message = "";

		if (user == null) {
			message = String.format("%s 이메일이 존재하지 않습니다. 회원가입을 진행해주세요.", email);
			ServletResponseTemplate.success(response, message);
		} else if (!user.getPassword().equals(pwd)) {
			message = String.format("%s님! 비밀번호가 틀렸습니다. 다시 로그인해주세요.", email);
			ServletResponseTemplate.success(response, message);
		} else {
			message = String.format("%s님! 성공적으로 로그인 되었습니다.", user.getNickname());
			ServletContext context = getServletContext();
			context.setAttribute("loggedUserId", user.getId());
			ServletResponseTemplate.success(response, message, 1);
		}

	}
}