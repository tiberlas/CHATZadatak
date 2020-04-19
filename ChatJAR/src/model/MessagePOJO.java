package model;

import java.io.Serializable;
import java.util.Calendar;

public class MessagePOJO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String reciver;
	private String sender;
	private String header;
	private String subject;
	private Calendar creationDate;

	public MessagePOJO() {
		super();
	}
	
	public MessagePOJO(String reciver, String sender, String header, String subject) {
		super();
		this.reciver = reciver;
		this.sender = sender;
		this.header = header;
		this.subject = subject;
		this.creationDate = Calendar.getInstance();
	}
	
	public MessagePOJO(String reciver, String sender, String header, String subject, Calendar creationDate) {
		super();
		this.reciver = reciver;
		this.sender = sender;
		this.header = header;
		this.subject = subject;
		this.creationDate = creationDate;
	}

	public String getReciver() {
		return reciver;
	}

	public void setReciver(String reciver) {
		this.reciver = reciver;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "MessagePOJO [reciver=" + reciver + ", sender=" + sender + ", header=" + header + ", subject=" + subject
				+ ", creationDate=" + creationDate + "]";
	}
}
