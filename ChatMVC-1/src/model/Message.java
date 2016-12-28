package model;

import java.sql.Date;

public class Message {

	private long msgId;
	private User senderId;
	private User receiverId;
	private String mgs;
	private Date dateTime;
	private boolean status; // 1: Da xem - 2: Dang cho

	public Message() {
	}

	public Message(long msgId, User senderId, User receiverId, String mgs,
			Date dateTime, boolean status) {
		super();
		this.msgId = msgId;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.mgs = mgs;
		this.dateTime = dateTime;
		this.status = status;
	}

	public long getMsgId() {
		return msgId;
	}

	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}

	public User getSenderId() {
		return senderId;
	}

	public void setSenderId(User senderId) {
		this.senderId = senderId;
	}

	public User getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(User receiverId) {
		this.receiverId = receiverId;
	}

	public String getMgs() {
		return mgs;
	}

	public void setMgs(String mgs) {
		this.mgs = mgs;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
