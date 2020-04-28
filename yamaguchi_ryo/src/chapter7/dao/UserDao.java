package chapter7.dao;

import static chapter7.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import chapter7.beans.User;
import chapter7.exception.NoRowsUpdatedRuntimeException;
import chapter7.exception.SQLRuntimeException;

public class UserDao {

	public void insert(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO users ( ");
			sql.append("login_id");
			sql.append(", password");
			sql.append(", name");
			sql.append(", branch_id");
			sql.append(", division_role_id");
			sql.append(", active_stop");
			sql.append(", created_date");
			sql.append(", updated_date");
			sql.append(") VALUES (");
			sql.append("?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", '活動中'");
			sql.append(", CURRENT_TIMESTAMP");
			sql.append(", CURRENT_TIMESTAMP");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLoginId());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setInt(4, user.getBranchId());
			ps.setInt(5, user.getDivisionRoleId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	//
	private List<User> toUserList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String name = rs.getString("name");
				int branchId = rs.getInt("branch_id");
				int divisionRoleId = rs.getInt("division_role_id");
				String userStopOrActive = rs.getString("active_stop");
				Timestamp createdDate = rs.getTimestamp("created_date");
				Timestamp updatedDate = rs.getTimestamp("updated_date");
				String divisionRoleName = rs.getString("division_role_name");
				String branchName = rs.getString("branch_name");

				User user = new User();
				user.setId(id);
				user.setLoginId(loginId);
				user.setName(name);
				user.setBranchId(branchId);
				user.setDivisionRoleId(divisionRoleId);
				user.setUserStopOrActive(userStopOrActive);
				user.setCreatedDate(createdDate);
				user.setUpdatedDate(updatedDate);
				user.setDivisionRoleName(divisionRoleName);
				user.setBranchName(branchName);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public List<User> selectUsers(Connection connection, int id) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT"
					+ " users.id,"
					+ "users.login_id,"
					+ "users.name,"
					+ "users.branch_id,"
					+ "users.division_role_id,"
					+ "users.active_stop,"
					+ "users.created_date,"
					+ "users.updated_date,"
					+ "branch_names.branch_name,"
					+ "division_role_names.division_role_name"
					+ " FROM users"
					+ " INNER JOIN branch_names"
					+ " ON users.branch_id = branch_names.branch_id"
					+ " INNER JOIN  division_role_names"
					+ " ON users.division_role_id = division_role_names.division_role_id";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			return userList;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public User selectUser(Connection connection, int id) {
		PreparedStatement ps = null;
		try {

			String sql = "SELECT "
					+ "users.id,"
					+ "users.login_id,"
					+ "users.name,"
					+ "users.branch_id,"
					+ "users.division_role_id,"
					+ "users.active_stop,"
					+ "users.created_date,"
					+ "users.updated_date,"
					+ "branch_names.branch_name,"
					+ "division_role_names.division_role_name"
					+ " FROM users"
					+ " INNER JOIN branch_names"
					+ " ON users.branch_id = branch_names.branch_id"
					+ " INNER JOIN  division_role_names"
					+ " ON users.division_role_id = division_role_names.division_role_id"
					+ " where users.id = " + id;

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<User> editUser = toUserList(rs);
			if (editUser.isEmpty() == true) {
				return null;
			} else if (2 <= editUser.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return editUser.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void updateUser(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			if(StringUtils.isEmpty(user.getPassword())) {
				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE users SET");
				sql.append("  login_id = ?");
				sql.append(", name = ?");
				sql.append(", branch_id = ?");
				sql.append(", division_role_id = ?");
				sql.append(", updated_date = CURRENT_TIMESTAMP");
				sql.append(" WHERE");
				sql.append(" id = ?");

				ps = connection.prepareStatement(sql.toString());

				ps.setString(1, user.getLoginId());
				ps.setString(2, user.getName());
				ps.setInt(3, user.getBranchId());
				ps.setInt(4, user.getDivisionRoleId());
				ps.setInt(5, user.getId());
			} else {
				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE users SET");
				sql.append("  login_id = ?");
				sql.append(", password = ?");
				sql.append(", name = ?");
				sql.append(", branch_id = ?");
				sql.append(", division_role_id = ?");
				sql.append(", updated_date = CURRENT_TIMESTAMP");
				sql.append(" WHERE");
				sql.append(" id = ?");

				ps = connection.prepareStatement(sql.toString());

				ps.setString(1, user.getLoginId());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getName());
				ps.setInt(4, user.getBranchId());
				ps.setInt(5, user.getDivisionRoleId());
				ps.setInt(6, user.getId());
			}


			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	public void updateStatus(Connection connection, User user) {
		PreparedStatement ps = null;
		String sql = null;
		try {
			int id = user.getId();
			if(user.getUserStopOrActive().equals("活動中")) {
				sql = "UPDATE users set active_stop = '停止中', updated_date = CURRENT_TIMESTAMP where id =" + id;
			} else {
				sql = "UPDATE users set active_stop = '活動中', updated_date = CURRENT_TIMESTAMP where id =" + id;
			}

			ps = connection.prepareStatement(sql);

			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}

