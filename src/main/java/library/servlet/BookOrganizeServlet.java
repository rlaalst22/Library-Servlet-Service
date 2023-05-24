package library.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.servlet.model.Book;

public class BookOrganizeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookOrganizeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//      response.getWriter().append("Served at: ").append(request.getContextPath());
		PreparedStatement pstmtSelect = null;
		PreparedStatement pstmtDelete = null;
		Connection con = null;
		ResultSet rs = null;
		int deleteCnt = 0;
		String message = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");


			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book?serverTimezone=UTC", "root",
					"1234");

			String selectQuery = "SELECT * FROM book WHERE book_condition = 0;";
			String deleteQuery = "DELETE FROM book WHERE book_condition = 0;";

			pstmtSelect = con.prepareStatement(selectQuery);
			rs = pstmtSelect.executeQuery();
			while (rs.next()) {
				Book book = new Book(rs);
			}

			pstmtDelete = con.prepareStatement(deleteQuery);
			deleteCnt = pstmtDelete.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmtSelect != null)
					pstmtSelect.close();
				if (pstmtDelete != null)
					pstmtDelete.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				System.out.println(se.getMessage());
			}
		}

		if (deleteCnt > 0) {
			message += deleteCnt + "개의 책이 상태가 안좋아서 제거 됐습니다.";
		}

		request.setAttribute("message", message);
		request.getRequestDispatcher("booklist.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}