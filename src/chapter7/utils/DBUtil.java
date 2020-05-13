package chapter7.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import chapter7.exception.SQLRuntimeException;

public class DBUtil {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/yamaguchi_ryo";
	private static final String USER = "root";
<<<<<<< HEAD
	private static final String PASSWORD = "root";
=======
	private static final String PASSWORD = "";
>>>>>>> 81ea4bbb504106228511d6e2ef54a2c47c9a0163

	static {

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}


	/**
	 * コネクションを取得します。
	 *
	 * @return
	 */
	public static Connection getConnection() {

		try {
			Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

			connection.setAutoCommit(false);

			return connection;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	/**
	 * コミットします。
	 *
	 * @param connection
	 */
	public static void commit(Connection connection) {

		try {
			connection.commit();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	/**
	 * ロールバックします。
	 *
	 * @param connection
	 */
	public static void rollback(Connection connection) {

		if (connection == null) {
			return;
		}

		try {
			connection.rollback();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}
}
