package com.googlecode.common.remote.pool.impl;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

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
	public Response list() throws Exception {
 		Class<?> superclass = getObjectPoolImpl().getClass().getSuperclass();
		Field declaredField = superclass.getDeclaredField("_pool");
		declaredField.setAccessible(true);

		String result=declaredField.get(getObjectPoolImpl()).toString();
		LOG.info("before handle: "+result);

      	if(result.equalsIgnoreCase("[]"))
     		 result="no any resource";

      	LOG.info("after handle one: "+result);

    	result=result.replace(", {", "<br>{");
    	result=result.replace("[", "");
    	result=result.replace("]", "");

    	result+="<br>";

    	result=handleListResult(result);
       	LOG.info("after handle two: "+result);

		return Response.ok(result, MediaType.TEXT_PLAIN_TYPE).build();
	}

	@GET
	@Path("listconfig")
	public Response listConfig() throws Exception {
 		return Response.ok(DefaultResourceFactory.backupList.toString(), MediaType.TEXT_PLAIN_TYPE).build();
	}

 	private String handleListResult(String str){
 		StringBuffer sb = new StringBuffer();
 		Pattern compile = Pattern.compile(";(.*?)<br>");
		Matcher matcher = compile.matcher(str);

		while (matcher.find()) {
			String group = matcher.group(1);
			long sstime = Long.valueOf(group);
			Date date = new Date(sstime);
 			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  			matcher.appendReplacement(sb, ":" + sdf.format(date)+"<br>");
		}

		matcher.appendTail(sb);

		return sb.toString();
 	}


	private GenericObjectPoolImpl getObjectPoolImpl() {
		return GenericObjectPoolImpl.getInstance();
	}

}
