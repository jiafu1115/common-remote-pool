package com.googlecode.common.remote.pool.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.annotations.Form;
import org.jboss.resteasy.spi.BadRequestException;

import com.googlecode.common.remote.pool.exception.NoResourceCanUsedException;
import com.googlecode.common.remote.pool.util.PoolUtil;

@Path("object")
public class ResourcePoolService {

	private final static Logger LOG=Logger.getLogger(ResourcePoolService.class);
	private final static Set<Object> ADDED_OBJECTS=new HashSet<Object>();

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
	public synchronized Object borrow() throws Exception {
		Object borrowObject = getObjectPoolImpl().borrowObject();
		if (borrowObject == null)
			throw new NoResourceCanUsedException();

		return borrowObject;
	}

	 @POST
	 @Path("borrow")
 	 @Produces(MediaType.APPLICATION_JSON)
	 public synchronized Object borrowWithCondition(@Form ResouceAddForm form) throws Exception {
         int numIdle = getObjectPoolImpl().getNumIdle();
         System.out.println(numIdle);
         if(numIdle==0)
             throw new NoResourceCanUsedException();

	     String originalJsonContent = form.getJsonContent();
	     System.out.println(originalJsonContent);
	        if (originalJsonContent.isEmpty()) {
	            throw new BadRequestException("content is empty");
	        }
	        String jsonContent = originalJsonContent.trim();
	        LOG.info("jsonContent:" + jsonContent);
	        List<Object> objectList=new ArrayList<Object>();
	        try {
	            if (jsonContent.startsWith("[")) {
	                ObjectMapper objectMapper = new ObjectMapper();
	                Object[] readValue = objectMapper.readValue(jsonContent,
	                        Object[].class);
	                for (Object object : readValue) {
 	                    objectList.add(object);
 	                }

	            } else {
	                ObjectMapper objectMapper = new ObjectMapper();
	                Object object = objectMapper.readValue(jsonContent,
	                        Object.class);
                    objectList.add(object);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return Response.status(500).entity("FAIL:" + e.getMessage())
	                    .build();
	        }

 	        for (int i = 0; i < numIdle; i++) {
	            Object borrowObject = getObjectPoolImpl().borrowObject();
	            if (borrowObject == null)
	                throw new NoResourceCanUsedException();
	            else{
	                for (Object object : objectList) {
	                    LOG.info("object condition:" + object);
	                    LOG.info("object borrowObject:" + borrowObject);

	                    if(object.toString().equals(borrowObject.toString())){
	                        return borrowObject;
	                    }
	                }

	                getObjectPoolImpl().returnObject(borrowObject);
	            }
            }

            throw new NoResourceCanUsedException();
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
        GenericObjectPoolImpl.resetPoolImpl();
        return Response.status(200).entity("OK").build();
	}

	@POST
	@Path("return")
	@Consumes(MediaType.APPLICATION_JSON)
	public synchronized void returnObject(Object object) throws Exception {
		getObjectPoolImpl().returnObject(object);
	}

	@POST
	@Path("add")
	public synchronized Response addObject(@Form ResouceAddForm form) {
		LOG.info("begin to add resource:");
 		String originalJsonContent = form.getJsonContent();
		if (originalJsonContent.isEmpty()) {
			throw new BadRequestException("content is empty");
		}
		String jsonContent = originalJsonContent.trim();
		LOG.info("jsonContent:" + jsonContent);
		try {
			if (jsonContent.startsWith("[")) {
				ObjectMapper objectMapper = new ObjectMapper();
				Object[] readValue = objectMapper.readValue(jsonContent,
						Object[].class);
				for (Object object : readValue) {
					ADDED_OBJECTS.add(object);
					PoolUtil.returnObjectWithoutActiveNumberChanage(object);
				}

			} else {
				ObjectMapper objectMapper = new ObjectMapper();
				Object object = objectMapper.readValue(jsonContent,
						Object.class);
				ADDED_OBJECTS.add(object);
				PoolUtil.returnObjectWithoutActiveNumberChanage(object);
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
	public synchronized Response getActiveNumber() throws Exception {
		GenericObjectPoolImpl objectPoolImpl = getObjectPoolImpl();
		int activeNumber = objectPoolImpl.getNumActive();

		return Response.ok(activeNumber, MediaType.TEXT_PLAIN_TYPE).build();
	}

	@GET
	@Path("info")
	public synchronized Response getPoolInfo() throws Exception {
		GenericObjectPoolImpl objectPoolImpl = getObjectPoolImpl();
		int activeNumber = objectPoolImpl.getNumActive();
		int idleNumber=objectPoolImpl.getNumIdle();
		int totalNumber=activeNumber+idleNumber;

		return Response.ok(new PoolInfo(activeNumber,idleNumber,totalNumber), MediaType.APPLICATION_JSON_TYPE).build();
	}

	@GET
	@Path("idle")
	public synchronized Response getIdleNumber() throws Exception {
		int activeNumber = getObjectPoolImpl().getNumIdle();
 		return Response.ok(activeNumber, MediaType.TEXT_PLAIN_TYPE).build();
	}

	private static GenericObjectPoolImpl getObjectPoolImpl() {
		return GenericObjectPoolImpl.getInstance();
	}
}
