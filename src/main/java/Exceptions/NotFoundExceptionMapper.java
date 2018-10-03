package Exceptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author Gonners
 */
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Context
    ServletContext context;

    @Override
    public Response toResponse(NotFoundException ex) {
        boolean isDebug = context.getInitParameter("debug").equals("true");
        exceptions.ErrorMessage err = new exceptions.ErrorMessage(ex, 404, isDebug);
        err.setDescription("NotFound");
        return Response.status(404)
                .entity(gson.toJson(err))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
