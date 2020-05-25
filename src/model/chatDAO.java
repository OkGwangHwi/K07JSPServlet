package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

public class chatDAO {

	Connection con;
	PreparedStatement psmt;
	ResultSet rs;
	
	public chatDAO() {
		try {
			Context initCtx = new InitialContext();
			/*Context ctx = (Context)initCtx.lookup("java:comp/env");
			DataSource source = (DataSource)ctx.lookup("jdbc/myoracle");*/
			//위2번의 lookup을 아래 1번으로 병합할 수 있다.
			DataSource source = (DataSource)initCtx.lookup("java:comp/env/jdbc/myoracle");
			
			con = source.getConnection();
			System.out.println("DBCP연결성공");
		}
		catch(Exception e) {
			System.out.println("DBCP연결실패");
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
	
	public List<BbsDTO> selectList(Map<String, Object> map){
		List<BbsDTO> bbs = new Vector<BbsDTO>();
		
		//기본쿼리문
		String query = "SELECT * FROM board ";
		
		//검색어가 있는경우 조건절 동적 추가
		if(map.get("Word") != null) {
			query += " WHERE "+ map.get("Column")+" "+" LIKE '%"+map.get("Word")+"%'";
		}
		
		//최근게시물이 항상 위로 노출되야 하므로 작성된 순서의 역순으로 정렬한다.
		query +=" ORDER BY num DESC";
		try {
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();
			//오라클이 반환해준 ResultSet의 갯수만큼 반복한다.
			while(rs.next()) {
				//하나의 레코드를 DTO객체에 저장하기 위해 새로운 객체생성
				BbsDTO dto = new BbsDTO();
				//setter()메소드를 사용하여 컬럼에 데이터 저장
				dto.setNum(rs.getString(1));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString(3));
				dto.setPostDate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString(6));
				
				//저장된 DTO객체를 List컬렉션에 추가
				bbs.add(dto);
			}
		}
		catch(Exception e) {
			System.out.println("Select시 예외발생");
			e.printStackTrace();
		}
		return bbs;
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
