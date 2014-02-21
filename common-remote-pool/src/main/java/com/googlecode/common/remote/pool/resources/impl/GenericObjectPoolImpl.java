package com.googlecode.common.remote.pool.resources.impl;


import org.apache.commons.pool.impl.GenericObjectPool;

public class GenericObjectPoolImpl extends GenericObjectPool<Extension>{

	private static GenericObjectPoolImpl INSTANCE;

	private GenericObjectPoolImpl() {
		super(new ExtensionFactory());
	}

	public static GenericObjectPoolImpl getInstance() {
		if (INSTANCE != null)
			return INSTANCE;

		synchronized (GenericObjectPoolImpl.class) {
			if (INSTANCE != null)
				return INSTANCE;

			INSTANCE = new GenericObjectPoolImpl();
			return INSTANCE;
		}

	}

}
