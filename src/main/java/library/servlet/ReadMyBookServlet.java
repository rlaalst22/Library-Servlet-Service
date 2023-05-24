package library.servlet;

import java.io.IOException;

import java.io.PrintWriter;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import library.servlet.model.User;
import library.servlet.model.Book;

public class ReadMyBookServlet extends HttpServlet {

	public ReadMyBookServlet() {
		super();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		ServletContext context = getServletContext();
		String id = (String) context.getAttribute("loggedUserId");
		int userId = Integer.parseInt(id);

		ResultSet rs = null;

		PreparedStatement pstmt = null;

		Connection con = null;

		List<Book> bookList = new ArrayList<>(); // 책 전체정보 담기위한 컬렉션

		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book?serverTimezone=UTC", "root",
					"1234");

			String query = "SELECT * FROM book.book WHERE rent_user_id = (?) ORDER BY return_date;";

			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, userId);

			// sql구문 실행하기
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Book book = new Book(rs);
				bookList.add(book);
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

		request.setAttribute("bookList", bookList); // 데이터를 request에 저장
		request.getRequestDispatcher("booklist.jsp").forward(request, response); // JSP로 제어를 넘김

	}
}