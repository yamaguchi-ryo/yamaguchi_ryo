package chapter7.dao;

import static chapter7.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chapter7.beans.User;
import chapter7.exception.SQLRuntimeException;

public class BranchDao {

	public List<User> selectBranches(Connection connection, int id) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM branches";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toBranchList(rs);
			return userList;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	//
	private List<User> toBranchList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int branchId = rs.getInt("id");
				String branchName = rs.getString("name");

				User user = new User();
				user.setBranchId(branchId);
				user.setBranchName(branchName);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public List<User> selectDivrols(Connection connection, int id) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM divisions_roles";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<User> ret = toDivrolList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	//
	private List<User> toDivrolList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int divrolId = rs.getInt("id");
				String divrolName = rs.getString("name");

				User user = new User();
				user.setDivisionRoleId(divrolId);
				user.setDivisionRoleName(divrolName);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}
