# Library-Servlet-Service
Java Library CRUD using Servlet, JSP and Naver Search Book API

<p align="center">

  <br>

<img width="1432" alt="booklist" src="https://github.com/zihooy/Java-Library-CRUD/assets/101122651/1fb9f856-ae8b-4005-9841-699de0c87708">
	
<img width="1432" alt="signup" src="https://github.com/zihooy/Java-Library-CRUD/assets/101122651/e96f5890-9863-4169-904c-8c1c785835ba">

<img width="1432" alt="booksearch" src="https://github.com/zihooy/Java-Library-CRUD/assets/101122651/3245b527-826b-41a6-b34b-3c77987cdeca">

<img width="1245" alt="page_structure" src="https://github.com/zihooy/Java-Library-CRUD/assets/101122651/d33d1a0f-c01e-4e36-aa47-63c927d0fd53">


   <br>

</p>

## 프로젝트 소개

<p align="justify">
    Java Servlet을 활용하여 도서 CRUD 프로그램을 제작하였습니다. 
    도서 조회, 추가, 정렬, 검색 등이 가능하며,
    도서 Create 시에는 Naver Search Book API를 활용하여 직접 책 정보를 입력하지 않고 Naver 도서에서 가져온 정보를 그대로 추가할 수 있도록 하였습니다.
</p>

### 구현한 기본 기능
 1. 회원가입
    1. 닉네임
    2. ID
    3. 비밀번호
    4. 개인정보 동의 확인
    5. 닉네임 중복 처리 기능
    6. 입력하지 않은 필드 확인 기능
2. 로그인
    1. 회원 존재 유무 확인
    2. 비밀번호 일치 확인
3. 도서 추가 (네이버 API) 
    1. 도서명 
    2. 작가명 
    3. 출판사 
    4. 출판일
    5. 가격
    6. 도서 상태 (상/중/하)
    7. ID, 등록일은 자동으로 생성
4. 도서 삭제
    1. 도서 책 상태가 [하]인 책 일괄 삭제
5. 도서 조회
    1. 전체 목록 조회
    2. 제목으로 조회
    3. 인기순 조회 (대여자 수를 기준으로 내림차순 정렬)
    4. 최신책 조회 (등록일을 기준으로 오름차순 정렬)
6. 대여
    1. 대여 가능 도서 목록 조회
    2. 대여 버튼 클릭 시 대여 완료
7. 반납
    1. 대여중인 목록 조회 (반납일을 기준으로 오름차순 정렬)
    2. 반납 버튼 클릭 시 반납 완료
8. 끝내기

### 추가 기능 
- Naver Search API 사용
    - Naver Search API를 통해 검색한 도서의 정보를 가져와 바로 DB에 Insert
    - [Naver Search API](https://developers.naver.com/docs/serviceapi/search/book/book.md)     
- 도서 상태 컬럼을 통한 기증 도서 관리
    - 도서마다 [상, 중, 하] 상태 존재
    - 도서 상태에 따른 Tag 구
- 대여, 반납
    - 대여 반납에 따른 Tag 구현
    - 다른 유저가 대여 시 대여 불
- 교보문고 UI를 참고하여 디자인
    - [교보문고](https://product.kyobobook.co.kr/bestseller/online?period=001#?page=1&per=20&ymw=&period=001&saleCmdtClstCode=&dsplDvsnCode=000&dsplTrgtDvsnCode=001&saleCmdtDsplDvsnCode=)
- 외부 라이브러리
    - Json Simple: API 결과를 가져오기 위한 라이브러리
    - JSTL: JSP에서 상용되는 태그 라이브러리
    - MySQL: Java에서 mysql 데이터 베이스 연결용 라이브러리

### DB Schema 
Table Book

![image](https://github.com/zihooy/Java-Library-CRUD/assets/101122651/d43af612-abb3-4f64-8762-4d4ca0fe3044)

Table User

![image](https://github.com/zihooy/Java-Library-CRUD/assets/101122651/dbd8572f-3185-4ecc-be64-b21622a06810)


        
### 코드 구조

  <img width="1315" alt="code_structure" src="https://github.com/zihooy/Java-Library-CRUD/assets/101122651/ebbe14c0-2dba-40c5-bc9f-b0f4f4413854">
  
### 실행시 유의 사항
- DB를 생성하고 servlet 폴더 안에 각 connection의 주소, 사용자, 비밀번호를 입력해 주어야 함.
```
con = DriverManager.getConnection("{YOUR_DB_URL}", "{YOUR_DB_USER}", "{YOUR_DB_PASSWORD}");

```
