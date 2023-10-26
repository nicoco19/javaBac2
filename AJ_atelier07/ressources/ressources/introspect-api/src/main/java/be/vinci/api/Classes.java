package be.vinci.api;

import be.vinci.services.ClassAnalyzer;
import be.vinci.classes.User;
import jakarta.json.JsonStructure;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

/**
 * Send class data to make class diagrams
 * The class name must be given, and present into the "classes" package
 */
@Path("classes")
public class Classes {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonStructure getClassInfo(@QueryParam("classname") String classname) throws NoSuchFieldException {
        Class c = null;
        try {
            c = Class.forName("be.vinci.classes." + classname);
        }catch (ClassNotFoundException ex){
            throw new WebApplicationException(404);
        }
        ClassAnalyzer analyzer = new ClassAnalyzer(c);
        return analyzer.getFullInfo();
    }
}
