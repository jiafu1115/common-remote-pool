package com.googlecode.common.remote.pool.impl;

import java.util.EmptyStackException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.annotations.Form;
import org.jboss.resteasy.spi.BadRequestException;

import com.googlecode.common.remote.pool.exception.NoResourceCanUsedException;

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
	public Object borrow() throws Exception {
		Object borrowObject = getObjectPoolImpl().borrowObject();
		if (borrowObject == null)
			throw new NoResourceCanUsedException();

		return borrowObject;
	}

	@GET
	@Path("getFactory")
	public Response getFactory() {
		String factory = GenericObjectPoolImpl.getClassForResourceFactory();
		return Response.ok(factory, MediaType.TEXT_PLAIN_TYPE).build();
	}

	@GET
	@Path("drain")
	public Response drain() throws Exception {
		try {
			while (true) {
				Object borrowObject = getObjectPoolImpl().borrowObject();
				if (borrowObject == null)
					break;
			}
			return Response.status(200).entity("OK").build();
		} catch (EmptyStackException e) {
			return Response.status(200).entity("OK").build();
		}
	}

	@POST
	@Path("return")
	@Consumes(MediaType.APPLICATION_JSON)
	public void returnObject(Object object) throws Exception {
		getObjectPoolImpl().returnObject(object);
	}

	@POST
	@Path("add")
	public Response addObject(@Form ResouceAddForm form) {
		System.out.println("reach here");

		String jsonContent2 = form.getJsonContent();
		if (jsonContent2.isEmpty()) {
			throw new BadRequestException("content is empty");
		}
		String jsonContent = jsonContent2.trim();
		System.out.println("jsonContent:" + jsonContent);
		try {
			if (jsonContent.startsWith("[")) {
				ObjectMapper objectMapper = new ObjectMapper();
				Object[] readValue = objectMapper.readValue(jsonContent,
						Object[].class);
				for (Object object : readValue) {
					returnObject(object);
				}

			} else {
				ObjectMapper objectMapper = new ObjectMapper();
				Object object = objectMapper.readValue(jsonContent,
						Object.class);
				returnObject(object);
			}
			return Response.status(200).entity("OK").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("FAIL:" + e.getMessage())
					.build();
		}

	}

	/**
	 * 
	 * 
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("active")
	public Response getIdleNumber() throws Exception {
		int activeNumber = getObjectPoolImpl().getNumActive();
		if (activeNumber < 0) {
			activeNumber = -activeNumber;
		}
		return Response.ok(activeNumber, MediaType.TEXT_PLAIN_TYPE).build();
	}

	private GenericObjectPoolImpl getObjectPoolImpl() {
		return GenericObjectPoolImpl.getInstance();
	}
}
