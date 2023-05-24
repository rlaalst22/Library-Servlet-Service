package library.servlet.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Book {
	/*
	 * BOOK_ID NUMBER TITLE VARCHAR2(50 BYTE) AUTHOR VARCHAR2(20 BYTE) PUBLISHER
	 * VARCHAR2(20 BYTE) PUBLISHER_DATE DATE PRICE NUMBER
	 */

	private int bookId; // 도서 번호
	private String title; // 도서명
	private String author; // 작가명
	private String publisher; // 출판사
	private String publisherDate; // 출판일 - String형식으로 받아 입력예정
	private Integer bookCondition; // 책 외관 상태
	private Integer price; // 가격
	private Integer rentState; // 대여상태
	private int rentUserId; // 대여자 명
	private Date rentDate; // 대여일
	private Date returnDate; // 반납일
	private int rentCount; // 누적 대여 횟수
	private Date createdDate; // 등록일
	private String description; // 책 설명
	private String isbn; // 책 고유 번호
	private String link; // 책 쇼핑 링크
	private String image; // 책 이미지 링크

	// 생성자
	public Book() {
	}

	public Book(int bookId, String title, String author, String publisher, String publisherDate, Integer bookCondition,
			Integer price, Integer rentState, int rentUserId, Date rentDate, Date returnDate, int rentCount,
			Date createdDate) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.publisherDate = publisherDate;
		this.bookCondition = bookCondition;
		this.price = price;
		this.rentState = rentState;
		this.rentUserId = rentUserId;
		this.rentDate = rentDate;
		this.returnDate = returnDate;
		this.rentCount = rentCount;
		this.createdDate = createdDate;
	}

	public Book(int bookId, String title, String author, String publisher, String publisherDate, Integer bookCondition,
			Integer price, Integer rentState, int rentUserId, Date rentDate, Date returnDate, int rentCount,
			Date createdDate, String description, String isbn, String link, String image) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.publisherDate = publisherDate;
		this.bookCondition = bookCondition;
		this.price = price;
		this.rentState = rentState;
		this.rentUserId = rentUserId;
		this.rentDate = rentDate;
		this.returnDate = returnDate;
		this.rentCount = rentCount;
		this.createdDate = createdDate;
		this.description = description;
		this.isbn = isbn;
		this.link = link;
		this.image = image;
	}

	// 매개변수 생성자 - bookId는 BOOK_SEQ 이용하여 입력 예정
	public Book(String title, String author, String publisher, String publisherDate, int price) {
		super();
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.publisherDate = publisherDate;
		this.price = price;
	}

	// 매개변수 생성자
	public Book(int bookId, String title, String author, String publisher, String publisherDate, int price) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.publisherDate = publisherDate;
		this.price = price;
	}

	public Book(ResultSet rs) throws SQLException {
		int bookId = rs.getInt("BOOK_ID");
		String title = rs.getString("TITLE");
		String author = rs.getString("AUTHOR");
		String publisher = rs.getString("PUBLISHER");
		String publisherDate = rs.getString("PUBLISHER_DATE");
		int bookCondition = rs.getInt("book_condition");
		int price = rs.getInt("PRICE");
		int rentState = rs.getInt("rent_state");
		int rentUserId = rs.getInt("rent_user_id");
		Date rentDate = rs.getDate("rent_date");
		Date returnDate = rs.getDate("return_date");
		int rentCount = rs.getInt("rent_count");
		Date createdDate = rs.getDate("created_date");
		String description = rs.getString("description");
		String isbn = rs.getString("isbn");
		String link = rs.getString("link");
		String image = rs.getString("image");

		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.publisherDate = publisherDate;
		this.bookCondition = bookCondition;
		this.price = price;
		this.rentState = rentState;
		this.rentUserId = rentUserId;
		this.rentDate = rentDate;
		this.returnDate = returnDate;
		this.rentCount = rentCount;
		this.createdDate = createdDate;
		this.description = description;
		this.isbn = isbn;
		this.link = link;
		this.image = image;
	}

	public Book(String title, String author, String publisher, String publisherDate, int price, int bookCondition) {
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.publisherDate = publisherDate;
		this.price = price;
		this.bookCondition = bookCondition;
	}

	public Book(String image, String author, String isbn, String link, String discount, String publisher,
	         String description, String title, String pubdate) {
	      super();
	      this.title = title;
	      this.author = author;
	      this.publisher = publisher;
	      this.publisherDate = pubdate;
	      this.price = Integer.valueOf(discount);
	      this.image = image;
	      this.isbn = isbn;
	      this.description = description;
	      this.link = link;

	   }

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublisherDate() {
		return publisherDate;
	}

	public void setPublisherDate(String publisherDate) {
		this.publisherDate = publisherDate;
	}

	public Integer getBookCondition() {
		return bookCondition;
	}

	public void setBookCondition(Integer bookCondition) {
		this.bookCondition = bookCondition;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getRentState() {
		return rentState;
	}

	public void setRentState(Integer rentState) {
		this.rentState = rentState;
	}

	public int getRentUserId() {
		return rentUserId;
	}

	public void setRentUserId(int rentUserId) {
		this.rentUserId = rentUserId;
	}

	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public int getRentCount() {
		return rentCount;
	}

	public void setRentCount(int rentCount) {
		this.rentCount = rentCount;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", author=" + author + ", publisher=" + publisher
				+ ", publisherDate=" + publisherDate + ", bookCondition=" + bookCondition + ", price=" + price
				+ ", rentState=" + rentState + ", rentUserId=" + rentUserId + ", rentDate=" + rentDate + ", returnDate="
				+ returnDate + ", rentCount=" + rentCount + ", createdDate=" + createdDate + ", description="
				+ description + ", isbn=" + isbn + ", link=" + link + ", image=" + image + "]";
	}

}