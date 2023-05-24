package library.servlet;

import java.io.IOException;

import java.io.PrintWriter;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import library.servlet.common.CallApi;
import library.servlet.model.Book;
import library.servlet.model.User;

public class BookSearchServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public BookSearchServlet() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");
      String text = request.getParameter("keyword");
//      System.out.println(text.isBlank());
//      System.out.println(text.isEmpty());
//      System.out.println(text);
      if (text.isEmpty()!=true) {
         CallApi a = new CallApi();
         
         ArrayList<Book> bookList = a.getJson(text);
         request.setAttribute("bookList", bookList); // 데이터를 request에 저장
         request.getRequestDispatcher("bookSearch.jsp").forward(request, response); // JSP로 제어를 넘김
         
      }else {
         request.getRequestDispatcher("bookSearch.jsp").forward(request, response); // JSP로 제어를 넘김
         
      }
//      System.out.println(text);
//      for (Book book : bookList) {
//         System.out.println(book);
//      }


   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}