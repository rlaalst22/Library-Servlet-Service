package library.servlet;

import java.io.IOException;

import java.io.PrintWriter;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Timestamp;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import library.servlet.model.Book;
import library.servlet.model.User;

import java.util.ArrayList;
import java.util.List;

public class ListServlet extends HttpServlet {


	public ListServlet() {
		super();
	}

	@Override
	   protected void service(HttpServletRequest request, HttpServletResponse response)
	         throws ServletException, IOException {
		List<User> userList = new ArrayList<>(); // 책 전체정보 담기위한 컬렉션

	      request.setCharacterEncoding("UTF-8");
	   
//	      System.out.println(pwd);
	      
	      //TODO: 중복 닉네임 처리 
//	      if(userRepository.duplicateById(nickname)) {
//	         String errorMessage = String.format("중복되는 아이디가 있습니다.");
//	         ServletResponseTemplate.fail(response, errorMessage);
//	         return;
//	      }
	      
	      int n=0;

	      PreparedStatement pstmt = null;
	      PreparedStatement pstmtSelect = null;
	      ResultSet rs = null;

	      Connection con = null;
	      int rowCnt = 0;
	      String isAvail = new String();

	      try{
	         Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book?serverTimezone=UTC", "root","1234");

//	         con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book?serverTimezone=UTC", "root","1234");
	         String SearchUserQuery = "SELECT * FROM BOOK_USER ";
	         pstmtSelect = con.prepareStatement(SearchUserQuery);

	         rs = pstmtSelect.executeQuery();

	         
	         while (rs.next()) {
	            User user = new User(rs);
	            userList.add(user);
	            
	         }
	       
	      } catch (ClassNotFoundException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } catch(SQLException se){

	         System.out.println(se.getMessage());

	      }finally{

	         try{

	            if(pstmt!=null) pstmt.close();

	            if(con!=null) con.close();

	         }catch(SQLException se){

	            System.out.println(se.getMessage());

	         }
	      }

//	      userRepository.signUp(User.create(id, email, pwd, phone));
//	      String message = String.format("%s님! " + isAvail, nickname);
//	      ServletResponseTemplate.success(response, message);
	  

		ServletResponseTemplate.success(response, userList);
	}

}