package chapter7.beans;

import java.io.Serializable;
import java.util.Date;


public class User implements Serializable {


	private int id;
	private String loginId;
	private String password;
	private String name;
	private int branchId;
	private int divisionRoleId;
	private int userStopOrActive;
	private Date createdDate;
	private Date updatedDate;
	private String divisionRoleName;
	private String branchName;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public int getDivisionRoleId() {
		return divisionRoleId;
	}
	public void setDivisionRoleId(int divisionRoleId) {
		this.divisionRoleId = divisionRoleId;
	}
	public int getUserStopOrActive() {
		return userStopOrActive;
	}
	public void setUserStopOrActive(int userStopOrActive) {
		this.userStopOrActive = userStopOrActive;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getDivisionRoleName() {
		return divisionRoleName;
	}
	public void setDivisionRoleName(String divisionRoleName) {
		this.divisionRoleName = divisionRoleName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

}
