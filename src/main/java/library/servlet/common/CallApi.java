package library.servlet.common;

import library.servlet.model.Book;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CallApi {
   // 네이버 검색 API 예제 - 블로그 검색
    public ArrayList<Book> getJson(String text) {
        String clientId = ""; //애플리케이션 클라이언트 아이디
        String clientSecret = ""; //애플리케이션 클라이언트 시크릿


//        String text = null;
        try {
            text = URLEncoder.encode(text, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }
//        int displayCount = 100;

        String apiURL = "https://openapi.naver.com/v1/search/book.json?query=" + text;    // JSON 결과
        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // XML 결과


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);
        JSONParser parser = new JSONParser();
        ArrayList<Book> resultList = new ArrayList<>(); 
        try{
            JSONObject jsonObject = (JSONObject) parser.parse(responseBody);
            JSONArray itemsArray = (JSONArray) jsonObject.get("items");
            for (Object itemObj : itemsArray) {
               JSONObject item = (JSONObject) itemObj;
               String image = (String) item.get("image");
                String author = (String) item.get("author");
                String isbn = (String) item.get("isbn");
                String link = (String) item.get("link");
                String discount = (String) item.get("discount");
                String publisher = (String) item.get("publisher");
                String description = (String) item.get("description");
                String title = (String) item.get("title");
                
                String pubdate = (String) item.get("pubdate");
                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

                // 출력 형식을 지정합니다.
                DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                // 입력 문자열을 LocalDate로 변환합니다.
                LocalDate date = LocalDate.parse(pubdate, inputFormatter);

                // 지정된 출력 형식으로 날짜를 문자열로 변환합니다.
                pubdate = date.format(outputFormatter);
                
                resultList.add(new Book(image, author, isbn, link, discount, publisher, description, title, pubdate));
         }
        } catch (ParseException e){
            e.printStackTrace();
        }

       return resultList;
    }


    private String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 오류 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }
}
