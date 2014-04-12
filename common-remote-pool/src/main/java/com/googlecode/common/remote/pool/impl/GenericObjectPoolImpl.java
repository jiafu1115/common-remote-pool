package com.googlecode.common.remote.pool.impl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;

import com.googlecode.common.remote.pool.exception.ResourceFactoryClassNoUploadException;

public class GenericObjectPoolImpl extends GenericObjectPool<Object> {

	private static final String DEFAULT_RESOURCE_FACTORY = "com.googlecode.common.remote.pool.resource.ResourceFactory";
	private static String classForResourceFactory = DEFAULT_RESOURCE_FACTORY;

	private static GenericObjectPoolImpl INSTANCE;

	public static GenericObjectPoolImpl getInstance() {
		if (INSTANCE != null)
			return INSTANCE;

		synchronized (GenericObjectPoolImpl.class) {
			if (INSTANCE != null)
				return INSTANCE;

			try {
				System.out.println("need reload GenericObjectPoolImpl");
				INSTANCE = new GenericObjectPoolImpl();
			} catch (ClassNotFoundException e) {
				throw new ResourceFactoryClassNoUploadException(
						"no right resource factory class uploaded, the current enable class is: "
								+ classForResourceFactory, e);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			}
			return INSTANCE;
		}

	}

	@SuppressWarnings("unchecked")
	private GenericObjectPoolImpl() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		super(getFactoryInstance());
	}

	private static PoolableObjectFactory<Object> getFactoryInstance()
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		InputStream resourceAsStream = GenericObjectPoolImpl.class
				.getClassLoader().getResourceAsStream("config.txt");
		try {
			String string = IOUtils.toString(resourceAsStream);
			System.out.println("import:" + string);
			if (string == null || string.isEmpty()) {
				classForResourceFactory = DEFAULT_RESOURCE_FACTORY;
			} else {
				classForResourceFactory = string;
			}

			System.out.println("new classForResourceFactory: "
					+ classForResourceFactory);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(resourceAsStream);
		}
		return (PoolableObjectFactory<Object>) Class.forName(
				classForResourceFactory).newInstance();
	}

	public static void resetPoolImpl(String newResourceFactory) {
		INSTANCE = null;
		classForResourceFactory = newResourceFactory;
	}

	public static String getClassForResourceFactory() {
		return classForResourceFactory;
	}

}
