package com.googlecode.common.remote.pool.impl;

public enum OperationType {

	BORROW, RETURN;

	public String toString() {
		return super.toString().toLowerCase();
	}
}
