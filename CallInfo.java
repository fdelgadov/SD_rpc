import java.util.*;
import java.io.*;

public class CallInfo implements Serializable {
    String service;
    String method;
    Object[] params;
    
    public CallInfo(String service, String method, Object[] params) {
        this.service = service;
        this.method = method;
        this.params = params;
    }
}
