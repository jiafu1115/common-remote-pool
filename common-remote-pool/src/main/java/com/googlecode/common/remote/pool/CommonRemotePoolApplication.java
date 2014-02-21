package com.googlecode.common.remote.pool;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.googlecode.common.remote.pool.impl.ResourcePool;

public class CommonRemotePoolApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();

	public CommonRemotePoolApplication() {
		initResources();
	}

	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

	private void initResources() {
		singletons.add(new ResourcePool());

	}

}
