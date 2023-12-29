package SQL;

import com.example.javafxmw.HelloApplication;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySQLDemo {
	// MySQL 5.x版本
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	// 注意数据库的名字要根据实际情况来，这里数据库名字是app
	static final String DB_URL = "jdbc:mysql://localhost:3306/app";
	static final String URL_EXTRA_PARA = "?characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false";
	// 数据库的用户名与密码，需要根据自己的设置
	static final String USER = "root";
	static final String PASS = "";


	private static Connection connection;
	// 预编译的Statement，可以有效避免SQL注入
	private static PreparedStatement pstmt = null;
	// 预编译命令
	static String addEquationSQL = "INSERT INTO EquationTable (equation, result) VALUES (?, ?)";


	public MySQLDemo() {
		connectToDatabase();
	}


	//数据库连接方法
	private static void connectToDatabase() {
		try {
			System.out.println("加载类从而注册驱动");
			Class.forName(JDBC_DRIVER);

			System.out.println("连接数据库...");
			connection = DriverManager.getConnection(DB_URL + URL_EXTRA_PARA, USER, PASS);

			// 连接相关代码
			//
			//
			//

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 获取数据库连接实例
//	public static Connection getConnection() {
//		return connection;
//	}

	// 关闭数据库连接
	public static void closeConnection() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	// 创建算式表
//	private static void createEquationTable(Statement stmt) throws SQLException {
//		// 创建一个SQL语句，用于创建一个EquationTable表
//		String createTableSQL = "CREATE TABLE IF NOT EXISTS EquationTable (id INT AUTO_INCREMENT, equation VARCHAR(255),result VARCHAR(255), PRIMARY KEY (id))";
//		// 执行SQL语句
//		stmt.executeUpdate(createTableSQL);
//		// 打印创建表的提示信息
//		System.out.println("算式表已创建或已存在！");
//	}

	// 插入算式数据
	public static void addEquation() throws SQLException {

		try {
			// 创建预处理语句
			pstmt = connection.prepareStatement(addEquationSQL);

			// 插入数据
			for (int i = 0; i < HelloApplication.Equations.length; i++) {
				pstmt.setString(1, HelloApplication.Equations[i].toString());
				pstmt.setString(2, String.valueOf(HelloApplication.correctAnswers[i]));
				pstmt.executeUpdate();
			}
			System.out.println("算式已插入成功！");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	// 查询算式数据
	public static List<Map<String, Object>> selectEquations() throws SQLException {
		// 查询EquationTable表中的所有数据
		String selectEquationsSQL = "SELECT * FROM EquationTable";
		List<Map<String, Object>> equationsList = new ArrayList<>();
		try (PreparedStatement pstmt = connection.prepareStatement(selectEquationsSQL);
			 ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				Map<String, Object> equationMap = new HashMap<>();
				// 获取查询结果中的id、equation、result字段
				int id = rs.getInt("id");
				String equation = rs.getString("equation");
				String result = rs.getString("result");

				// 将查询结果封装到Map中
				equationMap.put("id", id);
				equationMap.put("equation", equation);
				equationMap.put("result", result);

				// 将封装后的Map添加到List中
				equationsList.add(equationMap);
			}
		}
		return equationsList;
	}

	// 更新算式数据
//	private static void updateEquation(Statement stmt, int id, String newEquation) throws SQLException {
//		String updateEquationSQL = "UPDATE EquationTable SET equation = '" + newEquation + "' WHERE id = " + id;
//		stmt.executeUpdate(updateEquationSQL);
//		System.out.println("算式已更新成功！");
//	}

	// 删除算式数据
//	private static void deleteEquation(Statement stmt, int id) throws SQLException {
//		String deleteEquationSQL = "DELETE FROM EquationTable WHERE id = " + id;
//		int rowsAffected = stmt.executeUpdate(deleteEquationSQL);
//		System.out.println("已删除 " + rowsAffected + " 条算式数据！");
//	}
}