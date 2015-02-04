package com.googlecode.common.remote.pool.impl;

import java.util.Date;

public class BorrowInfo {

 	public BorrowInfo(String borrower, OperationType borrowType, Date date,
			Object object) {
		super();
		this.borrower = borrower;
		this.borrowType = borrowType;
		this.date = date;
		this.object = object;
	}
	
	private String borrower;
	private OperationType borrowType;
	private Date date;
	private Object object;
	public String getBorrower() {
		return borrower;
	}
	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}
	public OperationType getBorrowType() {
		return borrowType;
	}
	public void setBorrowType(OperationType borrowType) {
		this.borrowType = borrowType;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	@Override
	public String toString() {
		return  date+" "+borrower + "  "
				+ borrowType + "  " + object;
	}
	
 	public String toShortString() {
		return  date+" ["+borrower + "]  "
				+ borrowType ;
	}
	
 

}
