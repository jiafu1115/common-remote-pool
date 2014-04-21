package com.googlecode.common.remote.pool.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.annotations.Form;
import org.jboss.resteasy.spi.BadRequestException;

import com.googlecode.common.remote.pool.exception.NoResourceCanUsedException;
import com.googlecode.common.remote.pool.resource.DefaultResourceFactory;

@Path("object")
public class DefaultResourcePoolService {

	private final static Logger LOG=Logger.getLogger(DefaultResourcePoolService.class);


	private static DefaultResourcePoolService INSTANCE;

	public static DefaultResourcePoolService getInstance() {
		if (INSTANCE != null) {
			return INSTANCE;
		}

		synchronized (DefaultResourcePoolService.class) {
			if (INSTANCE != null) {
				return INSTANCE;
			}

			INSTANCE = new DefaultResourcePoolService();
			return INSTANCE;
		}
	}

 	@GET
	@Path("list")
	public Response list() {
		Stack<Object> stack = DefaultResourceFactory.getStack();

		List<Object> list=new ArrayList<Object>();
		for(int i=0;i<stack.size();i++){
			list.add(stack.elementAt(i));
		}

 		System.out.println(list);
 		JSONArray fromObject = JSONArray.fromObject(list);
 		System.out.println(fromObject);
   		return Response.ok(fromObject.toString(), MediaType.APPLICATION_JSON_TYPE).build();
	}


}
