package com.moxi.model;

import java.sql.Date;

public class NewsPersonnel extends BaseObject {

	private long id;
	private String name;
	private String sex;
	private String sr;
	private String phone;
	private String homeadd;
	private String email;
	private int departmentid;
	private String departmentname;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	private Date addDate;
	private int state;

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}


	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSr() {
		return sr;
	}

	public void setSr(String sr) {
		this.sr = sr;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHomeadd() {
		return homeadd;
	}

	public void setHomeadd(String homeadd) {
		this.homeadd = homeadd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(int departmentid) {
		this.departmentid = departmentid;
	}

	public String getDepartmentname() {
		return departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	public void setId(long id) {
		this.id = id;
	}

}
