package com.googlecode.common.remote.pool.util;

import java.lang.reflect.Method;

import com.googlecode.common.remote.pool.impl.GenericObjectPoolImpl;

public final class PoolUtil {

	private PoolUtil() {
		// TODO Auto-generated constructor stub
	}

	public static void returnObjectWithoutActiveNumberChanage(Object object) throws Exception {
		GenericObjectPoolImpl objectPoolImpl = GenericObjectPoolImpl.getInstance();
		Class<?> superclass = objectPoolImpl.getClass().getSuperclass();
		System.err.println("add to pool"+object);
		Method method = superclass.getDeclaredMethod("addObjectToPool",Object.class, boolean.class);
		method.setAccessible(true);
		method.invoke(objectPoolImpl,object, false);
	}

}
