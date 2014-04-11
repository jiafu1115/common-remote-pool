package com.googlecode.common.remote.pool.impl;

import java.io.IOException;
import java.util.EmptyStackException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jboss.resteasy.annotations.Form;

import com.googlecode.common.remote.pool.resource.upload.FactorySettingForm;

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
                return Response.status(Response.Status.NOT_FOUND).entity("no resource can be used").build();
            return borrowObject;
        } catch (EmptyStackException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("no resource can be used").build();
        } catch (ClassNotFoundException e) {
            return Response
                    .status(Response.Status.NOT_ACCEPTABLE)
                    .entity("no right factory class uploaded, the current enable class is: "
                            + GenericObjectPoolImpl.classForResourceFactory).build();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @GET
    @Path("drain")
    public Response drain() {
        try {
            while (true) {
                Object borrowObject = getObjectPoolImpl().borrowObject();
                if (borrowObject == null)
                    break;
            }
            return Response.status(200).entity("OK").build();
        } catch (EmptyStackException e) {
            return Response.status(200).entity("OK").build();
        } catch (ClassNotFoundException e) {
            return Response
                    .status(Response.Status.NOT_ACCEPTABLE)
                    .entity("no right factory class uploaded, the current enable class is: "
                            + GenericObjectPoolImpl.classForResourceFactory).build();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @POST
    @Path("return")
    @Consumes(MediaType.APPLICATION_JSON)
    public void returnObject(Object object) {
        try {
            getObjectPoolImpl().returnObject(object);
        } catch (ClassNotFoundException e) {
            Response.status(Response.Status.NOT_ACCEPTABLE).entity("no factory class uploaded").build();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @POST
    @Path("add")
    public Response addObject(@Form ResouceAddForm form) {
        System.out.println("reach here");
        String jsonContent = form.getJsonContent().trim();
        System.out.println("jsonContent:" + jsonContent);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Object object = objectMapper.readValue(jsonContent, Object.class);
            returnObject(object);
            return Response.status(200).entity("OK").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity("FAIL:" + e.getMessage()).build();
        }

    }

    /**
     * FIXME when no factory, it return 0
     *
     * @return
     */
    @GET
    @Path("active")
    public int getIdleNumber() {
        int activeNumber;
        try {
            activeNumber = getObjectPoolImpl().getNumActive();
            return activeNumber;

        } catch (ClassNotFoundException e) {
            Response.status(500).build();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        return 1;
    }

    private GenericObjectPoolImpl getObjectPoolImpl() throws ClassNotFoundException {
        return GenericObjectPoolImpl.getInstance();
    }
}
