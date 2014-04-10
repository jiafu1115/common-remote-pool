package com.googlecode.common.remote.pool.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("object")
public class ResourcePoolService {

	private static ResourcePoolService INSTANCE;

	public static ResourcePoolService getInstance() {
		if (INSTANCE != null) {
			return INSTANCE;
		}

		synchronized (ResourcePoolService.class) {
			if (INSTANCE != null) {
				return INSTANCE;
			}

			INSTANCE = new ResourcePoolService();
			return INSTANCE;
		}
	}

	@GET
	@Path("borrow")
	@Produces(MediaType.APPLICATION_JSON)
	public Object borrow() {
		try {
			Object borrowObject = getObjectPoolImpl().borrowObject();
			if (borrowObject == null)

				return Response.status(Response.Status.NOT_FOUND)
						.entity("no resource can be used").build();
			return borrowObject;
		} catch (ClassNotFoundException e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE)
					.entity("no factory class uploaded").build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@GET
	@Path("return")
	@Consumes(MediaType.APPLICATION_JSON)
	public void returnObject(Object object) {
		try {
			getObjectPoolImpl().returnObject(object);
		} catch (ClassNotFoundException e) {
			Response.status(Response.Status.NOT_ACCEPTABLE)
					.entity("no factory class uploaded").build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@GET
	@Path("active")
	public int getIdleNumber() {
		int activeNumber;
		try {
			activeNumber = getObjectPoolImpl().getNumActive();
			return activeNumber;

		} catch (ClassNotFoundException e) {
			Response.status(Response.Status.NOT_ACCEPTABLE)
					.entity("no factory class uploaded").build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return 0;
	}

	private GenericObjectPoolImpl getObjectPoolImpl()
			throws ClassNotFoundException {
		return GenericObjectPoolImpl.getInstance();
	}
}
