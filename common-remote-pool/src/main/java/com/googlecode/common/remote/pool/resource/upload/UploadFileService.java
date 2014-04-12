package com.googlecode.common.remote.pool.resource.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.Form;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import com.googlecode.common.remote.pool.impl.GenericObjectPoolImpl;

@Path("/file")
public class UploadFileService {

    private final String UPLOADED_FILE_PATH = UploadFileService.class.getClassLoader().getResource(".").getPath();

    @POST
    @Path("/upload")
    @Consumes("multipart/form-data")
    public Response uploadFile(@MultipartForm FileUploadForm form) {
        String fileName = form.getFileName() == null ? "Unknown" : form.getFileName();

        System.out.println(UPLOADED_FILE_PATH);
        System.out.println(fileName);

        String splits[] = fileName.split(Pattern.quote("."));

        System.out.println(Arrays.toString(splits));

        StringBuffer completeFilePath = new StringBuffer(UPLOADED_FILE_PATH);
        for (int i = 0; i < splits.length - 2; i++) {
            completeFilePath.append(splits[i]);
            completeFilePath.append("/");
        }
        completeFilePath.append(splits[splits.length - 2]);
        completeFilePath.append(".");
        completeFilePath.append(splits[splits.length - 1]);

        System.out.println(completeFilePath);

        try {
            // Save the file
            File file = new File(completeFilePath.toString());

            String parent = file.getParent();
            if (parent != null) {
                File parentFolder = new File(parent);
                if (!parentFolder.exists())
                    parentFolder.mkdirs();
            }

            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);

            fos.write(form.getFileData());
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            return Response
                    .status(500)
                    .entity("[FAIL]: uploadFile is called, Target Path: " + completeFilePath
                            + ", Uploaded file name : " + fileName).build();
        }
        // Build a response to return
        return Response
                .status(200)
                .entity("[SUCCESS]: uploadFile is called, Target Path: " + completeFilePath
                        + " , Uploaded file name : " + fileName).build();
    }

    @POST
    @Path("/setFactory")
    public Response setFactory(@Form FactorySettingForm form) {
        System.err.println(form.getFileName());
        String newResourceFactory = form.getFileName() == null ? "Unknown" : form.getFileName().trim();
        URL resource = UploadFileService.class.getClassLoader().getResource("config.txt");
        File file = new File(resource.getPath());
        file.deleteOnExit();

        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(newResourceFactory.getBytes());
            fos.flush();
            fos.close();

            GenericObjectPoolImpl.resetPoolImpl(newResourceFactory);
            System.out.println("set null to GenericObjectPoolImpl");
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(500).entity("[FAIL]: setFactory is called, set file name : " + newResourceFactory).build();
        }
        // Build a response to return
        return Response.status(200).entity("[SUCCESS]: setFactory is called, set file name : " + newResourceFactory).build();
    }

}
