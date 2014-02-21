package com.googlecode.common.remote.pool.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.pool.impl.GenericObjectPool;


@Path("object")
public class ResourcePool {

    private static ResourcePool INSTANCE;
    private GenericObjectPool<Object> genericObjectPool;


    public ResourcePool() {
        try {
             genericObjectPool =GenericObjectPoolImpl.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
         }
    }

    public static ResourcePool getInstance() {
        if (INSTANCE != null) {
            return INSTANCE;
        }

        synchronized (ResourcePool.class) {
            if (INSTANCE != null) {
                return INSTANCE;
            }

            INSTANCE = new ResourcePool();
            return INSTANCE;
        }
    }

    @GET
    @Path("borrow")
    @Produces(MediaType.APPLICATION_JSON)
    public Object borrow() {
        try {
            return genericObjectPool.borrowObject();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @GET
    @Path("return")
    @Consumes(MediaType.APPLICATION_JSON)
    public void returnObject(Object object) {
        try {
            genericObjectPool.returnObject(object);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @GET
    @Path("active")
    public int getIdleNumber() {
        int activeNumber = genericObjectPool.getNumActive();
        return activeNumber;
    }
}
