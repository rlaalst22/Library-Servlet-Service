package library.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

import library.servlet.model.User;

import java.util.List;

public class ServletResponseTemplate {

	public static void success(HttpServletResponse response, String message) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.println("<html>");
		pw.println("<head>\r\n" + "<meta charset=\"EUC-KR\">\r\n"
				+ "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "<title>5조 회원가입</title>\r\n" + "\r\n" + "<!-- Bootstrap CSS -->\r\n" + "<link rel=\"stylesheet\"\r\n"
				+ "	href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\"\r\n"
				+ "	integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\"\r\n"
				+ "	crossorigin=\"anonymous\">\r\n" + "\r\n" + "<style>\r\n" + "body {\r\n" + "	min-height: 100vh;\r\n"
				+ "	background: -webkit-gradient(linear, left bottom, right top, from(#92b5db),\r\n"
				+ "		to(#1d466c));\r\n"
				+ "	background: -webkit-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);\r\n"
				+ "	background: -moz-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);\r\n"
				+ "	background: -o-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);\r\n"
				+ "	background: linear-gradient(to top right, #92b5db 0%, #1d466c 100%);\r\n" + "}\r\n" + "\r\n"
				+ ".white-box {\r\n" + "	max-width: 680px;\r\n" + "	margin-top: 80px;\r\n" + "	padding: 32px;\r\n"
				+ "	background: #fff;\r\n" + "	-webkit-border-radius: 10px;\r\n" + "	-moz-border-radius: 10px;\r\n"
				+ "	border-radius: 10px;\r\n" + "	-webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);\r\n"
				+ "	-moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);\r\n"
				+ "	box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);\r\n" + "	height: 400px;\r\n" + "}\r\n"
				+ "a{color: blue, text-decoration: underline}" + "</style>\r\n" + "</head>");
		pw.println("<body>");
		pw.println("<div class=\"white-box col-md-12 mx-auto text-center\">");
		pw.println(message);
		pw.println("<br/>");
		pw.println("<a href='/Servlet_Study/'>메인페이지로 이동</a>");
		pw.println("</div>");
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
	}

	public static void success(HttpServletResponse response, String message, int res) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.println("<html>");
		pw.println("<head>\r\n" + "<meta charset=\"EUC-KR\">\r\n"
				+ "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "<title>5조 회원가입</title>\r\n" + "\r\n" + "<!-- Bootstrap CSS -->\r\n" + "<link rel=\"stylesheet\"\r\n"
				+ "	href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\"\r\n"
				+ "	integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\"\r\n"
				+ "	crossorigin=\"anonymous\">\r\n" + "\r\n" + "<style>\r\n" + "body {\r\n" + "	min-height: 100vh;\r\n"
				+ "	background: -webkit-gradient(linear, left bottom, right top, from(#92b5db),\r\n"
				+ "		to(#1d466c));\r\n"
				+ "	background: -webkit-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);\r\n"
				+ "	background: -moz-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);\r\n"
				+ "	background: -o-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);\r\n"
				+ "	background: linear-gradient(to top right, #92b5db 0%, #1d466c 100%);\r\n" + "}\r\n" + "\r\n"
				+ ".white-box {\r\n" + "	max-width: 680px;\r\n" + "	margin-top: 80px;\r\n" + "	padding: 32px;\r\n"
				+ "	background: #fff;\r\n" + "	-webkit-border-radius: 10px;\r\n" + "	-moz-border-radius: 10px;\r\n"
				+ "	border-radius: 10px;\r\n" + "	-webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);\r\n"
				+ "	-moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);\r\n"
				+ "	box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);\r\n" + "	height: 400px;\r\n" + "}\r\n"
				+ "a{color: blue, text-decoration: underline}" + "</style>\r\n" + "</head>");
		pw.println("<body>");
		pw.println("<div class=\"white-box col-md-12 mx-auto text-center\">");
		pw.println(message);
		pw.println("<br/>");
		pw.println("<a href='/Servlet_Study/booklist'>도서 목록 확인하기</a>");
		pw.println("</div>");
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
	}

	public static void success(HttpServletResponse response, List<User> users) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.println("<html>");
		pw.println("<head>\r\n" + "<meta charset=\"EUC-KR\">\r\n"
				+ "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "<title>5조 회원가입</title>\r\n" + "\r\n" + "<!-- Bootstrap CSS -->\r\n" + "<link rel=\"stylesheet\"\r\n"
				+ "	href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\"\r\n"
				+ "	integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\"\r\n"
				+ "	crossorigin=\"anonymous\">\r\n" + "\r\n" + "<style>\r\n" + "body {\r\n" + "	min-height: 100vh;\r\n"
				+ "color: white'\r\n"
				+ "	background: -webkit-gradient(linear, left bottom, right top, from(#92b5db),\r\n"
				+ "		to(#1d466c));\r\n"
				+ "	background: -webkit-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);\r\n"
				+ "	background: -moz-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);\r\n"
				+ "	background: -o-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);\r\n"
				+ "	background: linear-gradient(to top right, #92b5db 0%, #1d466c 100%);\r\n" + "}\r\n" + "\r\n"
				+ ".white-box {\r\n" + "	max-width: 680px;\r\n" + "	margin-top: 80px;\r\n" + "	padding: 32px;\r\n"
				+ "	background: #fff;\r\n" + "	-webkit-border-radius: 10px;\r\n" + "	-moz-border-radius: 10px;\r\n"
				+ "	border-radius: 10px;\r\n" + "	-webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);\r\n"
				+ "	-moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);\r\n"
				+ "	box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);\r\n" + "	height: 400px;\r\n" + "}\r\n"
				+ "</style>\r\n" + "</head>");
		pw.println("<body>");
		pw.println("<div class=\"container\">");
		pw.println("<div class=\"row\">");
		pw.println("<div class=\"white-box col-md-12 mx-auto text-center\">");
		pw.println("<div>");
		pw.println("<table border='1' width='600'>");
		pw.println("<tr >");
		pw.println("<td>아이디</td>");
		pw.println("<td>닉네임</td>");
		pw.println("<td>이메일</td>");

		pw.println("</tr class=\"text-center\">");
		for (int i = 0; i < users.size(); i++) {
			pw.println("<tr>");
			pw.println("<td>" + (i + 1) + "</td>");
			pw.println("<td>" + users.get(i).getNickname() + "</td>");
			pw.println("<td>" + users.get(i).getEmail() + "</td>");

			pw.println("</tr>");
		}
//		for (User user : users) {
//
//		}
		pw.println("</table>");
		pw.println("</div>");
		pw.println("<div class=\"my-3 text-center\"> <a href='/Servlet_Study/'>메인페이지로 이동</a></div>");
		pw.println("</div>");
		pw.println("</div>");
		pw.println("<footer class=\"my-3 text-center text-small\">\r\n"
				+ "<p class=\"mb-1\">&copy; PoscoDX Java 전문가과정 408기 5조</p>\r\n" + "</footer>");
		pw.println("</div>");
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
	}

	public static void fail(HttpServletResponse response, String message) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.println("<html>");
		pw.println("<head>\r\n" + "<meta charset=\"EUC-KR\">\r\n"
				+ "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "<title>5조 회원가입</title>\r\n" + "\r\n" + "<!-- Bootstrap CSS -->\r\n" + "<link rel=\"stylesheet\"\r\n"
				+ "	href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\"\r\n"
				+ "	integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\"\r\n"
				+ "	crossorigin=\"anonymous\">\r\n" + "\r\n" + "<style>\r\n" + "body {\r\n" + "	min-height: 100vh;\r\n"
				+ "color: white'\r\n"
				+ "	background: -webkit-gradient(linear, left bottom, right top, from(#92b5db),\r\n"
				+ "		to(#1d466c));\r\n"
				+ "	background: -webkit-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);\r\n"
				+ "	background: -moz-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);\r\n"
				+ "	background: -o-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);\r\n"
				+ "	background: linear-gradient(to top right, #92b5db 0%, #1d466c 100%);\r\n" + "}\r\n" + "\r\n"
				+ ".white-box {\r\n" + "	max-width: 680px;\r\n" + "	margin-top: 80px;\r\n" + "	padding: 32px;\r\n"
				+ "	background: #fff;\r\n" + "	-webkit-border-radius: 10px;\r\n" + "	-moz-border-radius: 10px;\r\n"
				+ "	border-radius: 10px;\r\n" + "	-webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);\r\n"
				+ "	-moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);\r\n"
				+ "	box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);\r\n" + "	height: 400px;\r\n" + "}\r\n"
				+ "</style>\r\n" + "</head>");
		pw.println("<body>");
		pw.println("<div class=\"white-box col-md-12 mx-auto text-center\">");
		pw.println(message);
		pw.println("<br/>");
		pw.println("<a href='javascript:history.go(-1)'>이전페이지로 가기</a>");
		pw.println("</br>");
		pw.println("<a href='/Servlet_Study/'>메인페이지로 이동</a>");
		pw.println("</div>");
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
	}
}
