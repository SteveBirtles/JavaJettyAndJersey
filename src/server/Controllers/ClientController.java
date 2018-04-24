package server.Controllers;

import server.Console;

import javax.ws.rs.*;
import java.io.*;
import java.net.URL;

@Path("client/")
public class ClientController {

    @GET
    @Path("img/{path}")
    @Produces({"image/jpeg,image/png"})
    public byte[] getImageFile(@PathParam("path") String path) {
        return getFile("client/img/" + path);
    }

    @GET
    @Path("js/{path}")
    @Produces({"text/javascript"})
    public byte[] getJavaScriptFile(@PathParam("path") String path) {
        return getFile("client/js/" + path);
    }

    @GET
    @Path("css/{path}")
    @Produces({"text/css"})
    public byte[] getCSSFile(@PathParam("path") String path) {
        return getFile("client/css/" + path);
    }

    @GET
    @Path("{path}")
    @Produces({"text/html"})
    public byte[] getIHTMLFile(@PathParam("path") String path) {
        return getFile("client/" + path);
    }

    private byte[] getFile(String filename) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL fileURL = classLoader.getResource(filename);
            if (fileURL == null) {
                Console.log("Error, unable to send: " + filename);
            } else {
                File file = new File(fileURL.getFile());
                byte[] fileData = new byte[(int) file.length()];
                DataInputStream dis = new DataInputStream(new FileInputStream(file));
                dis.readFully(fileData);
                dis.close();
                Console.log("Sending: " + filename);
                return fileData;
            }
        } catch (IOException ioe) {
            Console.log("File IO error: " + ioe.getMessage());
        }
        return null;
    }

}