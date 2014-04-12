package com.googlecode.common.remote.pool.exception;
 
import java.util.EmptyStackException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
 
@Provider
 public class CommonRemotePoolExceptionMapper implements ExceptionMapper<Exception> 
{

	@Override
	public Response toResponse(Exception exception) {
		String message = exception.getMessage();
		System.out.println(message);
		if(exception instanceof ResourceFactoryClassNoUploadException || exception.getCause() instanceof ResourceFactoryClassNoUploadException){
	         return Response.status(Status.NOT_ACCEPTABLE).entity(message).build();  
 		}
		

		if(exception instanceof EmptyStackException || exception instanceof NoResourceCanUsedException){
			return Response.status(Response.Status.NOT_FOUND)
					.entity("no any resource can be used").build();
		}
   		
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(message).build();  
	}
 
}