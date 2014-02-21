package com.googlecode.common.remote.pool.resources;


import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;

public class GenericObjectPoolImpl extends GenericObjectPool<Object>{

    private final static String CLASS_FOR_RESOURCE_FACTORY="com.googlecode.common.remote.pool.resources.impl.ResourceFactory";

	@SuppressWarnings("unchecked")
    private GenericObjectPoolImpl() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		super((PoolableObjectFactory<Object>) Class.forName(CLASS_FOR_RESOURCE_FACTORY).newInstance());
	}

	private static GenericObjectPoolImpl INSTANCE;

	public static GenericObjectPoolImpl getInstance() {
        if (INSTANCE != null)
            return INSTANCE;

        synchronized (GenericObjectPoolImpl.class) {
            if (INSTANCE != null)
                return INSTANCE;

            try {
                INSTANCE = new GenericObjectPoolImpl();
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage(), e);
             }
            return INSTANCE;
        }

    }



}
