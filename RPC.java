import java.io.*;
import java.util.*;
import java.net.*;

public class RPC {
    static Map<String, Object> services = new HashMap<String, Object>();
    
    static void addService(String name, Object service) {
        services.put(name, service);
    }
    
    static Object execService(CallInfo ci){
        Object s = services.get(ci.service);
        Object res = null;
        Object[] p_ = new Object[ci.params.length];
        Class<?>[] types = new Class<?>[p_.length];
        for(int i = 0; i < p_.length; i++){
            p_[i] = ci.params[i];
            types[i] = p_[i].getClass();
        }
        try {
            res = s.getClass().getMethod(ci.method, types).invoke(s, p_);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    
    static Object callMethod(String ip, int port, CallInfo ci) {
        Socket s;
        try {
            s = new Socket(ip, port);
        }
        catch(Exception e) {
            System.out.println("No fue posible conectar a servidor " + ip + ":" + port);
            e.printStackTrace();
            return null;
        }
        try {
            new ObjectOutputStream(s.getOutputStream()).writeObject(ci);
        }
        catch(Exception e) {
            System.out.println("No se pudo llamar a " + ci.service + "." + ci.method);
            e.printStackTrace();
        }
        
        Object res = null;
        try {
            res = new ObjectInputStream(s.getInputStream()).readObject();
        } catch (Exception e) {
            System.out.println("No se pudo recibir respuesta");
            e.printStackTrace();
        }
        
        try {
            s.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        return res;
    }
}
