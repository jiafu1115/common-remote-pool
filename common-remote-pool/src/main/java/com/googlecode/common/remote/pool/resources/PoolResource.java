package com.googlecode.common.remote.pool.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.pool.impl.GenericObjectPool;



@Path("extension")
public class PoolResource {

	private static final GenericObjectPool EXTENSION_POOL = GenericObjectPool.getInstance();

	public PoolResource() {

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Extension borrow() {
		try {
			return EXTENSION_POOL.borrowObject();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public void returnObject(Extension extension) {
		try {
			EXTENSION_POOL.returnObject(extension);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@GET
	@Path("active")
	public int getIdleNumber() {
		int activeNumber = EXTENSION_POOL.getNumActive();
 		return activeNumber;
	}
}
