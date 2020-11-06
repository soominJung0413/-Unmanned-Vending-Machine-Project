package version1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Dao {

	// 상태-변수(id, pw, connection 정의)
	//static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	static String url = "jdbc:oracle:thin:@172.30.1.103:1521:xe";
	static String user = "javpay";
	static String password = "1234";
	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	static PreparedStatement pstmt = null;

	// 동작-메소드
	// getconnect(), sql수행 메소드, dbclose()
	static void getconnect() {
		if (conn == null) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				System.out.println("드라이버 에러는?=" + e.getMessage());
			} catch (SQLException e) {
				// System.out.println("접속 에러는?=");
				e.printStackTrace();
			}
		}
	}

	static void dbclose() {
		if (conn != null) {
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
			}
		}
		if (rs != null) {
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
				stmt = null;
			} catch (SQLException e) {
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
				pstmt = null;
			} catch (SQLException e) {
			}
		}
	}
/*
	public void selectAll(String table) {
		getconnect(); // DB접속
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM " + table;
			rs = stmt.executeQuery(sql);

			ResultSetMetaData rsmd = rs.getMetaData();
			int col = rsmd.getColumnCount();
			String temp = "";
			while (rs.next()) {
				for (int i = 1; i <= col; i++) {
					temp = rsmd.getColumnTypeName(i);
					if (!(temp.equals("NUMBER")))
						System.out.print(rs.getString(i) + "\t");
					else
						System.out.print(rs.getInt(i) + "\t");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			// System.out.println("쿼리 에러는?=");
		}
		dbclose(); // DB종료
	}
*/
	
	public CategoryVo getCategoryByKind(int kind) {
		CategoryVo temp = new CategoryVo();
		getconnect();
		try {
			String sql = "SELECT * FROM CATEGORY WHERE KIND=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, kind);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				temp.setKind(rs.getInt("KIND"));
				temp.setName(rs.getString("NAME"));
				temp.setDetail(rs.getString("DETAIL"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbclose();
		return temp;

	}
	
	public ProductVo getProductByProdid(int prodid) {
		ProductVo temp = new ProductVo();
		getconnect();
		try {
			String sql = "SELECT * FROM PRODUCT WHERE PRODID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prodid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				temp.setProdid(rs.getInt("PRODID"));
				temp.setName(rs.getString("NAME"));
				temp.setKind(rs.getInt("KIND"));
				temp.setPrice(rs.getInt("PRICE"));
				temp.setOldprice(rs.getInt("OLDPRICE"));
				temp.setCost(rs.getInt("COST"));
				temp.setContent(rs.getString("CONTENT"));
				temp.setStock(rs.getInt("STOCK"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbclose();
		return temp;

	}

	public int insertOrdered(int orderid, int prodid, int cnt, String pay) {
		int result = 0;
		getconnect(); // DB접속
		try {
			stmt = conn.createStatement();
			String sql = "INSERT INTO ORDERED (ORDERID, PRODID, CNT, PAY)" + " VALUES (" + orderid + "," + prodid + ","
					+ cnt + ",'" + pay + "')";
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// System.out.println("쿼리 에러는?=");
		}
		dbclose(); // DB종료
		return result;
	}
	
	public int updateStock(int prodid, int stock) {
		int result = 0;
		getconnect(); // DB접속
		try {
			String sql = "UPDATE PRODUCT SET STOCK=? WHERE PRODID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, stock);
			pstmt.setInt(2, prodid);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// System.out.println("쿼리 에러는?=");
		}
		dbclose(); // DB종료
		return result;
	}

	public int confirmManagerID(String id, String pw) {
		int rst = 0;
		getconnect();// 디비접속
		try {
			String sql = "SELECT COUNT(EMPLOYEE_ID) AS CNT FROM EMPS WHERE LOGINID=? and PASSWORD = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				rst = rs.getInt("CNT"); //// 추가파일@@ 로그인 아이디를 불러와서 대입하는 값.
			}
		} catch (SQLException e) {
			// 다음 에러값
			e.printStackTrace();
		}
		dbclose();
		return rst;

	}
	
	public Vector getSales() {
		Vector data = new Vector();
		try {
			getconnect();
			String sql = "SELECT P.PRODID AS 품번, NAME AS 품명, NVL(SUM(CNT),0) AS 판매량,"
					+ " NVL(SUM(PRICE*CNT),0) AS 매출, NVL(SUM((PRICE-COST)*CNT),0) AS 마진,"
					+ " STOCK AS 재고  FROM PRODUCT P, ORDERED O WHERE P.PRODID = O.PRODID(+)"
					+ " GROUP BY P.PRODID, NAME, STOCK ORDER BY P.PRODID";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int prodid = rs.getInt("품번");
				String name = rs.getString("품명");
				int cnt = rs.getInt("판매량");
				int mat = rs.getInt("매출");
				int mat2 = rs.getInt("마진");
				int stock = rs.getInt("재고");
				Vector row = new Vector();
				row.add(prodid);
				row.add(name);
				row.add(cnt);
				row.add(mat);
				row.add(mat2);
				row.add(stock);
				data.add(row);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
}
