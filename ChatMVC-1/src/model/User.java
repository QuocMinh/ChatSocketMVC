package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {

	private int userId;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private Date dob;
	private String ipAddr;

	public User() {
		super();
	}

	public User(String email, String password, String firstName,
			String lastName, Date dob, String ipAddr) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.ipAddr = ipAddr;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}
	
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	/**
	 * @param dob : YYYY/MM/DD
	 */
	public void setDob(String dob) {
		try {
			this.dob = new SimpleDateFormat("yyyy/MM/dd").parse(dob);
		} catch (ParseException e) {
			e.printStackTrace();
			this.dob = null;
		}
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	
}
