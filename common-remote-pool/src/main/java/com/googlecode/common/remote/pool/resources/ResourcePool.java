package com.googlecode.common.remote.pool.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.pool.impl.GenericObjectPool;



@Path("extension")
public class ResourcePool {

	private static final GenericObjectPool GenericObjectPoolImpl = GenericObjectPool.getInstance();

	public ResourcePool() {

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object borrow() {
		try {
			return GenericObjectPoolImpl.borrowObject();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public void returnObject(Object object) {
		try {
			GenericObjectPoolImpl.returnObject(object);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@GET
	@Path("active")
	public int getIdleNumber() {
		int activeNumber = GenericObjectPoolImpl.getNumActive();
 		return activeNumber;
	}
}
