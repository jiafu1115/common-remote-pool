package com.googlecode.common.remote.pool.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.pool.impl.GenericObjectPool;

@Path("extension")
public class ResourcePool {

    public static final String ClASSNAME_FOR_GENERIC_OBJECTPOOL_IMPL="com.googlecode.common.remote.pool.resources.impl.GenericObjectPoolImpl";

    private static ResourcePool INSTANCE;
    private GenericObjectPool<Object> GenericObjectPoolImpl;


    @SuppressWarnings("unchecked")
    public ResourcePool() {
        try {
             GenericObjectPoolImpl =(GenericObjectPool<Object>) Class.forName(ClASSNAME_FOR_GENERIC_OBJECTPOOL_IMPL).newInstance();
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
    @Produces(MediaType.APPLICATION_JSON)
    public Object borrow() {
        try {
            return GenericObjectPoolImpl.borrowObject();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void returnObject(Object object) {
        try {
            GenericObjectPoolImpl.returnObject(object);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @GET
    @Path("active")
    public int getIdleNumber() {
        int activeNumber = GenericObjectPoolImpl.getNumActive();
        return activeNumber;
    }
}
