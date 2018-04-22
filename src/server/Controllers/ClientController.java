package server.Controllers;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.*;
import java.net.URL;
import java.util.List;

@Path("client/")
public class ClientController {

    @GET
    @Path("img/{path: .*}")
    @Produces({"image/jpeg,image/png"})
    public byte[] getImageFile(@PathParam("path") List<PathSegment> path) {
        return getFile(getFilename("/img", path));
    }

    @GET
    @Path("js/{path: .*}")
    @Produces({"text/javascript"})
    public byte[] getJavaScriptFile(@PathParam("path") List<PathSegment> path) {
        String filename = getFilename("/js", path);
        if (filename.substring(filename.length() - 4, filename.length()).equals(".map")) {
            System.out.println("Ignoring JS map file: " + filename);
            return null;
        }
        return getFile(filename);
    }

    @GET
    @Path("css/{path: .*}")
    @Produces({"text/css"})
    public byte[] getCSSFile(@PathParam("path") List<PathSegment> path) {
        String filename = getFilename("/css", path);
        if (filename.substring(filename.length() - 4, filename.length()).equals(".map")) {
            System.out.println("Ignoring CSS map file: " + filename);
            return null;
        }
        return getFile(filename);
    }

    @GET
    @Path("{path: .*}")
    @Produces({"text/html"})
    public byte[] getIHTMLFile(@PathParam("path") List<PathSegment> path) {
        return getFile(getFilename("", path));
    }

    private String getFilename(String topPath, List<PathSegment> path)
    {
        StringBuilder filename = new StringBuilder("client" + topPath);
        for (PathSegment p : path) {
            filename.append("/").append(p.toString());
        }
        return filename.toString();
    }

    private byte[] getFile(String filename) {

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL fileURL = classLoader.getResource("" + filename);
            if (fileURL == null) {
                System.out.println("Error: Unable to send " + filename);
                return null;
            } else {
                File file = new File(fileURL.getFile());
                byte[] fileData = new byte[(int) file.length()];
                DataInputStream dis = new DataInputStream(new FileInputStream(file));
                dis.readFully(fileData);
                dis.close();
                System.out.println("Sending: " + filename);
                return fileData;
            }
        } catch (IOException ioe) {
            System.out.println("File IO error: " + ioe.getMessage());
        }
        return null;
    }

}