package chapter7.service;

import static chapter7.utils.CloseableUtil.*;
import static chapter7.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import chapter7.beans.User;
import chapter7.dao.BranchDao;

public class BranchlistService {
	private static final int LIMIT_NUM = 1000;

	public List<User> getBranches() {


		Connection connection = null;
		try {
			connection = getConnection();

			BranchDao branchlistDao = new BranchDao();
			List<User> ret = branchlistDao.selectBranches(connection, LIMIT_NUM);

			commit(connection);

			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}


	}




}
