package library.servlet.dao;

import static library.servlet.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import library.servlet.model.Book;

import java.util.Date;



public class BookDAO {

	// 4-1. 도서 목록 전체 조회
	public List<Book> bookSelectAll(Connection conn) {
		String query = "SELECT * FROM BOOK";
		Statement stmt = null;
		ResultSet rs = null;
		List<Book> bookList = new ArrayList<>(); // 책 전체정보 담기위한 컬렉션

		try {
			stmt = conn.createStatement();

			rs = stmt.executeQuery(query);
			while (rs.next()) {
				int bookId = rs.getInt("BOOK_ID");
				String title = rs.getString("TITLE");
				String author = rs.getString("AUTHOR");
				String publisher = rs.getString("PUBLISHER");
				String publisherDate = rs.getString("PUBLISHER_DATE");
				int bookCondition = rs.getInt("book_condition");
				int price = rs.getInt("PRICE");
				int rentState = rs.getInt("rent_state");
				String rentUser = rs.getString("rent_user");
				Date rentDate = rs.getDate("rent_date");
				Date returnDate = rs.getDate("return_date");
				int rentCount = rs.getInt("rent_count");
				Date createdDate = rs.getDate("created_date");

//				Book book = new Book(bookId, title, author, publisher, publisherDate, bookCondition, price, rentState,
//						rentUser, rentDate, returnDate, rentCount, createdDate);

//				bookList.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}

		return bookList;
	}

	// 1. 도서 추가
	// TODO : 입력시 추가 정보 필요
	public int bookInsert(Connection conn, Book b) {
		String query = "INSERT INTO BOOK (title, author, publisher, publisher_date, price, book_condition) " + "VALUES(?, ?, ?, ?, ?, ?)";
//		insert into book(title, author, publisher, publisher_date, price) values('민수책', '김민수', '민수출판사', '1999-04-29', 1000);
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getAuthor());
			pstmt.setString(3, b.getPublisher());
			pstmt.setString(4, b.getPublisherDate());
			pstmt.setInt(5, b.getPrice());
			pstmt.setInt(6, b.getBookCondition());


			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	// 3. 도서 삭제
	public int bookDelete(Connection conn, int bookId) {
		String query = "DELETE FROM BOOK " + "WHERE BOOK_ID = ?";
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookId);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	// 2. 도서 정보 수정
	public int bookUpdate(Connection conn, Book book) {
		String query = "UPDATE BOOK " + "SET TITLE = ?, AUTHOR = ?, PUBLISHER = ?, PUBLISHER_DATE = ?, PRICE = ?, BOOK_CONDITION = ?"
				+ " WHERE BOOK_ID = ?";
		PreparedStatement pstmt = null;
		int result = 0;

		List<Book> bookList = bookSelectId(conn, book.getBookId());
		Book innerBook = bookList.get(0);
		if(innerBook.getRentState()!=0) {
			System.out.println("대여중인 도서는 수정할 수 없습니다.");
			return -1;
		}

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, book.getPublisher());
			pstmt.setString(4, book.getPublisherDate());
			pstmt.setInt(5, book.getPrice());
			pstmt.setInt(6, book.getBookCondition());
			pstmt.setInt(7, book.getBookId());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	// 4-2. 도서 아이디로 조회
	public static List<Book> bookSelectId(Connection conn, int bookId) {
		String query = "SELECT * FROM BOOK " + "WHERE BOOK_ID = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Book> listBook = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookId);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Book book = new Book(rs);
				listBook.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return listBook;
	}


	// 4-3. 도서 제목으로 조회
	public List<Book> bookSelectTitle(Connection conn, String bookTitle) {
		String query = "SELECT * FROM BOOK " + "WHERE TITLE LIKE ('%' ? '%')";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Book> listBook = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bookTitle);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Book book = new Book(rs);
				listBook.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return listBook;
	}

	// 4-4 저자로 조회
	public List<Book> bookSelectAuthor(Connection conn, String bookAuthor) {
		String query = "SELECT * FROM BOOK " + "WHERE AUTHOR LIKE ('%' ? '%')";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Book> listBook = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bookAuthor);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Book b = new Book(rs);
				listBook.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return listBook;
	}

	// 4-5. 도서 출판사로 조회
	public List<Book> bookSelectPublisher(Connection conn, String bookPublisher) {
		String query = "SELECT * FROM BOOK " + "WHERE PUBLISHER LIKE ('%' ? '%')";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Book> listBook = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bookPublisher);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Book b = new Book(rs);
				listBook.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return listBook;
	}

	// 4-6. 도서 인기순 조회(대여횟수)
	public List<Book> bookSortedByRentCount(Connection conn) {
		String query = "SELECT * FROM BOOK " + "ORDER BY rent_count DESC";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Book> listBook = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(query);


			rs = pstmt.executeQuery();
			while (rs.next()) {
				Book b = new Book(rs);
				listBook.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return listBook;
	}
	// TODO: 4-7. 도서 최신책 조회
	public List<Book> bookSortedByPublishedDate(Connection conn) {
		String query = "SELECT * FROM BOOK " + "ORDER BY created_date DESC;";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Book> listBook = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(query);


			rs = pstmt.executeQuery();
			while (rs.next()) {
				Book b = new Book(rs);
				listBook.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return listBook;
	}
	// TODO: 5-1. 대여 가능 도서 목록 조회
	public static List<Book> bookSelectRentAvail(Connection conn){
		String query = "SELECT * FROM BOOK " + "WHERE RENT_STATE = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Book> bookList = new ArrayList<>();
		try{
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, 0);
			rs = pstmt.executeQuery();

			while(rs.next()){
				Book book = new Book(rs);
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return bookList;
	}

	// 5-2. 대여 상태 변경 및 대여자 명 업데이트(수정)
	public int bookRentUpdate(Connection conn, int bookId, String rentUser) {
		String query = "UPDATE BOOK " + "SET RENT_USER = ?, RENT_DATE = ? ,RETURN_DATE = ?, RENT_COUNT = ?, RENT_STATE = ?"
				+ " WHERE BOOK_ID = ?";
		PreparedStatement pstmt = null;
		int result = 0;

		List<Book> book = bookSelectId(conn, bookId);
		Book innerBook = book.get(0);

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime futureDateTime = now.plusDays(7);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDateTime = now.format(formatter);
//      System.out.println(formattedDateTime);

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, rentUser);
			pstmt.setString(2, formattedDateTime);
			pstmt.setString(3, futureDateTime.format(formatter));
			pstmt.setString(4, String.valueOf(innerBook.getRentCount()+1));
			pstmt.setInt(5, 1);
			pstmt.setInt(6, bookId);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	// 6-1. 대여자 명으로 대여 중 목록 조회
	public static List<Book> bookSelectReturnAvail(Connection conn, String rentUser){
		String query = "SELECT * FROM BOOK " + "WHERE RENT_USER = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Book> bookList = new ArrayList<>();
		try{
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, rentUser);
			rs = pstmt.executeQuery();

			while(rs.next()){
				Book book = new Book(rs);
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return bookList;
	}
	// 6-2. 아이디로 반납 상태 변경
	public int bookReturnUpdate(Connection conn, int bookId) {
		String query = "UPDATE BOOK " + "SET RENT_USER = ?, RENT_DATE = ? ,RETURN_DATE = ?, RENT_STATE = ?"
				+ " WHERE BOOK_ID = ?";
		PreparedStatement pstmt = null;
		int result = 0;

		List<Book> book = bookSelectId(conn, bookId);
		Book innerBook = book.get(0);

		LocalDateTime now = LocalDateTime.now();
		Date date = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
		if (innerBook.getReturnDate().before(date)) System.out.println("[※|경고|※] 반납 일자가 지났습니다.");
//      if(innerBook.getReturnDate() < now) return -1;
//      System.out.println(formattedDateTime);

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, null);
			pstmt.setString(2, null);
			pstmt.setString(3, null);
			pstmt.setInt(4, 0);
			pstmt.setInt(5, bookId);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

}