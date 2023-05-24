package library.servlet;

import java.io.IOException;

import java.io.PrintWriter;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import library.servlet.model.User;
import library.servlet.model.Book;

public class RentBookServlet extends HttpServlet {

	public RentBookServlet() {
		super();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String rentCount = request.getParameter("rentCount");
		String bookId = request.getParameter("bookId");

		ServletContext context = getServletContext();
		String id = (String) context.getAttribute("loggedUserId");
		int userId = Integer.parseInt(id);
		int count = Integer.valueOf(rentCount);
		int book_id = Integer.valueOf(bookId);

		int rs = 0;

		PreparedStatement pstmt = null;

		Connection con = null;

		List<Book> bookList = new ArrayList<>(); // 책 전체정보 담기위한 컬렉션

		String formattedDateTime = "";
		String futureDate = "";

		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book?serverTimezone=UTC", "root",
					"1234");

			String query = "UPDATE BOOK "
					+ "SET RENT_USER_ID = ?, RENT_DATE = ? ,RETURN_DATE = ?, RENT_COUNT = ?, RENT_STATE = ?"
					+ " WHERE BOOK_ID = ?";

			pstmt = con.prepareStatement(query);

			LocalDateTime now = LocalDateTime.now();
			LocalDateTime futureDateTime = now.plusDays(7);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			formattedDateTime = now.format(formatter);
			futureDate = futureDateTime.format(formatter);

			pstmt.setInt(1, userId);
			pstmt.setString(2, formattedDateTime);
			pstmt.setString(3, futureDateTime.format(formatter));
			pstmt.setInt(4, count + 1);
			pstmt.setInt(5, 1);
			pstmt.setInt(6, book_id);

			// sql구문 실행하기
			rs = pstmt.executeUpdate();

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

		if (rs > 0) {
			message += "책을 대여하였습니다.\n";
			message += "반납일은 ";
			message += futureDate;
			message += " 입니다.";
		}

		for (Book book : bookList) {
			System.out.println(book.toString());
		}
		request.setAttribute("message", message); // 데이터를 request에 저장
		request.getRequestDispatcher("booklist.jsp").forward(request, response); // JSP로 제어를 넘김

	}
}