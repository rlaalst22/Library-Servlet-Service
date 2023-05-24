package library.servlet;

import java.io.IOException;

import java.io.PrintWriter;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.print.attribute.standard.PrinterIsAcceptingJobs;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import library.servlet.model.Book;
import library.servlet.model.User;

public class SignUpServlet extends HttpServlet {

	public SignUpServlet() {
		super();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		String pwd = request.getParameter("password");
//      System.out.println(pwd);

		// TODO: 중복 닉네임 처리
//      if(userRepository.duplicateById(nickname)) {
//         String errorMessage = String.format("중복되는 아이디가 있습니다.");
//         ServletResponseTemplate.fail(response, errorMessage);
//         return;
//      }

		int n = 0;

		PreparedStatement pstmt = null;
		PreparedStatement pstmtSelect = null;
		ResultSet rs = null;

		Connection con = null;
		int rowCnt = 0;
		String isAvail = new String();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book?serverTimezone=UTC", "root",
					"1234");

			String SearchUserQuery = "SELECT * FROM BOOK_USER WHERE NICKNAME=? OR EMAIL=?";
			pstmtSelect = con.prepareStatement(SearchUserQuery);
			pstmtSelect.setString(1, nickname);
			pstmtSelect.setString(2, email);
			rs = pstmtSelect.executeQuery();
			while (rs.next()) {
				rowCnt++;
			}
			if (rowCnt == 0) {
				String query = "INSERT INTO BOOK_USER (nickname, email, pwd) " + "VALUES(?, ?, ?)";

				pstmt = con.prepareStatement(query);

				pstmt.setString(1, nickname);

				pstmt.setString(2, email);

				pstmt.setString(3, pwd);
				// sql구문 실행하기
				n = pstmt.executeUpdate();
				isAvail = "성공적으로 가입되었습니다.";
			} else
				isAvail = "중복닉네임 혹은 중복 이메일 입니다. 다시 회원가입을 해주세요.";
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

//      userRepository.signUp(User.create(id, email, pwd, phone));
		String message = String.format("%s님! " + isAvail, nickname);
		ServletResponseTemplate.success(response, message);
	}
}