package version2.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VendingMerchineDao {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@172.30.1.103:1521:xe";
	private static final String userId = "javpay";
	private static final String userPw = "1234";
	private static String sql = "";
	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	private static PreparedStatement preparedStatement = null;
	private ProductVo productVo;
	private static VendingMerchineDao vendingMerchineDao;

	public static VendingMerchineDao getVendingMerchineDao() {
		//getter입니다. 클라이언트당 하나의 프로그램을 사용하고 다시 시작하게 되겠다 싶어 싱글톤으로 주었습니다.
		if (vendingMerchineDao == null) {
			synchronized (VendingMerchineDao.class) {
				if (vendingMerchineDao == null) {
					vendingMerchineDao = new VendingMerchineDao();
				}
			}
		}
		return vendingMerchineDao;
	}

	public static void getConnect() {
		if (connection == null) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				System.out.println("ORACLE Driver 로딩실패 : " + e.getMessage());
				dbClose();
				return;
			}
			try {
				connection = DriverManager.getConnection(url, userId, userPw);
			} catch (SQLException e1) {
				System.out.println("Connection 객체 로딩실패 : " + e1.getMessage());
				return;
			}
			try {
				System.out.println("Connection 구동 : " + !connection.isClosed());
			} catch (SQLException e) {
			}
			return;
		}
		return;
	}

	public static void dbClose() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println("DB객체 Close 실패 : " + e.getMessage());
			return;
		}
		System.out.println("DataBase 정상종료.");
	}

	public ProductVo getProductByProdid(int prodid) {
		// assembler에서 물품번호로 i 값을 받아 쿼리문으로 확인하고 ProductVo객체를 로 매핑하여 리턴합니다. ProductVo가 사용합니다.
		getConnect();
		productVo = new ProductVo();
		try {
			sql = "SELECT * FROM PRODUCT WHERE PRODID=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, prodid);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				productVo.setProdid(resultSet.getInt("PRODID"));
				productVo.setName(resultSet.getString("NAME"));
				productVo.setKind(resultSet.getInt("KIND"));
				productVo.setPrice(resultSet.getInt("PRICE"));
				productVo.setOldprice(resultSet.getInt("OLDPRICE"));
				productVo.setCost(resultSet.getInt("COST"));
				productVo.setContent(resultSet.getString("CONTENT"));
				productVo.setStock(resultSet.getInt("STOCK"));
			}
		} catch (SQLException e) {
			System.out.println(" SQL 문 실행 중 에러발생 : " + e.getMessage());
		}
		return productVo;
	}


}
