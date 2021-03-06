package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;

public class chatDAO {

	Connection con;
	PreparedStatement psmt;
	ResultSet rs;
	
	public chatDAO(String driver,String url) {
		try {
		Class.forName(driver);
		String id = "kosmo";
		String pw = "1234";
		con = DriverManager.getConnection(url,id,pw);
		System.out.println("DB연결성공");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//인자생성자2
	/*
	 JSP에서는 application내장객체를 파라미터로 전달하고
	 생성자에서는 web.xml에 직접 접근한다. application내장객체는
	 javax.servlet.ServletContext타입으로 정의되었으므로
	 메소드에서 사용시에는 해당 타입으로 받아야한다.
	 ※각 내장객체의 타입은 JSP교안 "04.내장객체" 참조할것
	 */
	public chatDAO(ServletContext ctx) {
		try {
			Class.forName(ctx.getInitParameter("JDBCDriver"));
			String id = "kosmo";
			String pw = "1234";
			con = DriverManager.getConnection(
					ctx.getInitParameter("ConnectionURL"),id,pw);
			System.out.println("DB 연결성공");
		}
		catch(Exception e) {
			System.out.println("DB 연결실패");
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if(rs != null) rs.close();
			if(psmt != null) psmt.close();
			if(con != null) con.close();
		}
		catch(Exception e) {
			System.out.println("자원반납시 예외발생");
			e.printStackTrace();
		}
	}
	
	
	//접속자 아이디 저장
	public int insertId(chatDTO dto) {
		int affected = 0;
		try {
			String query = "INSERT INTO chat_id ( "
					+ " id) "
					+ " VALUES ( "
					+ " ?)";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getId());
			
			affected = psmt.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("insert중 예외발생");
			e.printStackTrace();
		}
		return affected;
	}
	
	public int sendMsg(chatDTO dto) {
		int affected = 0;
		try {
			String query = "INSERT INTO chatting ( "
					+ " num,id,msg,postdate) "
					+ " VALUES ( "
					+ " seq_board_num.NEXTVAL, ?, ? ,?)";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getMsg());
			psmt.setDate(3, dto.getPostDate());
			
			affected = psmt.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("insert중 예외발생");
			e.printStackTrace();
		}
		return affected;
		
	}
	
}