package com.googlecode.common.remote.pool.impl;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

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
	@Path("listAdd")
	public Response listAll() throws Exception {
 		JSONArray arry=JSONArray.fromObject(ResourcePoolService.ADDED_OBJECTS);
  		String string = arry.toString();
  		if(string.equalsIgnoreCase("[]"))
  			string="no any resource be added";
		return Response.ok(string, MediaType.TEXT_PLAIN_TYPE).build();
	}
 	
 	
 	@GET
	@Path("log")
	public Response log() throws Exception {
 		List<BorrowInfo> borrowInfoList = new ArrayList<BorrowInfo>(ResourcePoolService.getBorrowInfoList());
 		StringBuffer stringBuffer=new StringBuffer();
 		for (BorrowInfo borrowInfo : borrowInfoList) {
 			stringBuffer.append(borrowInfo.toString());
 			stringBuffer.append("<br>");
 		}
		return Response.ok(stringBuffer, MediaType.TEXT_PLAIN_TYPE).build();
	}
 	
 	
 	
 	@GET
	@Path("logsort")
	public Response logsort() throws Exception {
 		List<BorrowInfo> borrowInfoList = new ArrayList<BorrowInfo>(ResourcePoolService.getBorrowInfoList());
 		Map<Object,List<BorrowInfo>> map=new HashMap<Object,List<BorrowInfo>>();
 		for (BorrowInfo borrowInfo : borrowInfoList) {
 			List<BorrowInfo> list = map.get(borrowInfo.getObject());
			if(list==null){
 				ArrayList<BorrowInfo> value = new ArrayList<BorrowInfo>();
 				value.add(borrowInfo);
				map.put(borrowInfo.getObject(), value);
  			}else{
 				list.add(borrowInfo);
 			}
 		}
 		
 		StringBuffer stringBuffer=new StringBuffer();

 		Set<Object> keySet = map.keySet();
 		for (Object object : keySet) {
 			stringBuffer.append("------------------------------------------");
 			
 			stringBuffer.append("<br>");
 			
 			List<BorrowInfo> list = map.get(object);
 			if(list.size()!=0&&list.get(list.size()-1).getBorrowType().equals(OperationType.BORROW)){
  				stringBuffer.append("<font color=\"red\">"+object+"</font>");
   			}else{
  				stringBuffer.append("<font color=\"green\">"+object+"</font>");
   			}
 			stringBuffer.append("<br>");
 			
 			for (BorrowInfo borrowInfo : list) {
 	 			stringBuffer.append("      "+borrowInfo.toShortString());
 	 			stringBuffer.append("<br>");
  			}
 			
 			stringBuffer.append("<br>");

  
		}
 		  
		return Response.ok(stringBuffer, MediaType.TEXT_PLAIN_TYPE).build();
	}
 	
 	
	@GET
	@Path("clearlog")
	public Response clearlog() throws Exception {
		ResourcePoolService.getBorrowInfoList().clear();
		return Response.ok("OK", MediaType.TEXT_PLAIN_TYPE).build();
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

 	/**
	@GET
	@Path("listadded")
	public Response listConfig() throws Exception {
 		return Response.ok(DefaultResourceFactory.backupList.toString(), MediaType.TEXT_PLAIN_TYPE).build();
	}
	*/

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
