package library.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BookInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookInsertServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//      response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Enumeration<String> parameterNames = request.getParameterNames();

		Integer bookCondition = Integer.valueOf(request.getParameter("bookCondition"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		String publisherDate = request.getParameter("publisherDate");
		Integer price = Integer.valueOf(request.getParameter("price"));
		String image = request.getParameter("image");
		String isbn = request.getParameter("isbn");
		String description = request.getParameter("description");
		String link = request.getParameter("link");

//      while (parameterNames.hasMoreElements()) {
//         String paramName = parameterNames.nextElement();
//         String paramValue = request.getParameter(paramName);
//
//         System.out.println("Parameter Name: " + paramName);
//         System.out.println("Parameter Value: " + paramValue);
//      }
		PreparedStatement pstmt = null;
		Connection con = null;
		int n = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book?serverTimezone=UTC", "root",
					"1234");

			String query = "INSERT INTO BOOK (book_condition, title, author, publisher, "
					+ "publisher_date, price, image, " + "isbn, description, link) " + "VALUES(?, ?, ?,?,?,?,?,?,?,?)";

			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, bookCondition);
			pstmt.setString(2, title);
			pstmt.setString(3, author);
			pstmt.setString(4, publisher);
			pstmt.setString(5, publisherDate);
			pstmt.setInt(6, price);
			pstmt.setString(7, image);
			pstmt.setString(8, isbn);
			pstmt.setString(9, description);
			pstmt.setString(10, link);

			n = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
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

		if (n > 0) {
			message += "기증 완료 되었습니다.";
			message += "기증 감사합니다.";
		}

		request.setAttribute("contributeMessage", message); // 데이터를 request에 저장
		request.getRequestDispatcher("booklist.jsp").forward(request, response); // JSP로 제어를 넘김
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}