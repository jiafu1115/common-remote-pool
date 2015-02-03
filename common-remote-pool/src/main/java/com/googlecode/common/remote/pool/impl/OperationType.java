package com.googlecode.common.remote.pool.impl;

public enum OperationType {

	BORROW, RETURN, ADD;

	public String toString() {
		return super.toString().toLowerCase();
	}
}
