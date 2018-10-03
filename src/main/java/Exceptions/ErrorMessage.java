package exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * @author Gonners
 */
public class ErrorMessage {
    private int code;
    private String message;
    private String description;
    private String stackTrace;

    public ErrorMessage(Throwable ex, int code, boolean debug) {
        this.code = code;
        this.message = ex.getMessage();
        this.description = ex.getMessage();
        if (debug) {
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            this.stackTrace = sw.toString();
        }
    }

    public void setDescription(String desc) {
        this.description = desc;
    }
 
}
