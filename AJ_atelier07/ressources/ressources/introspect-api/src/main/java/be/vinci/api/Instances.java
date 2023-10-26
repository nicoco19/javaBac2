package be.vinci.api;

import be.vinci.classes.User;
import be.vinci.instances.InstanceGraph1;
import be.vinci.services.ClassAnalyzer;
import be.vinci.services.InstancesAnalyzer;
import jakarta.json.JsonStructure;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
//import be.vinci.utils.InstanceGraphBuilder;

import java.lang.reflect.Method;

/**
 * Send instances graph data to make object diagrams
 *
 * The instances graphs are initialized by a class containing the "initInstanceGraph" method,
 * building the instance graph, and returning it.
 *
 * The "instance builder class name" must be given and present into the "instances" package
 */
@Path("instances")
public class Instances {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonStructure getInstanceGraphInfo(@QueryParam("builderclassname") String builderClassname) {

        Class<?> c;
        try {
            c = Class.forName("be.vinci.instances." + builderClassname);
        }catch (ClassNotFoundException ex){
            throw new WebApplicationException(404);
        }

        InstanceGraph1 builder = new InstanceGraph1();    // TODO change this line to use the query parameter, and generate dynamically the builder
        Object instanceGraph = builder.initInstanceGraph();   // TODO change this line to avoid calling initInstanceGraph() directly
        InstancesAnalyzer analyzer = new InstancesAnalyzer(instanceGraph);
        return analyzer.getFullInfo();
    }
}
