import java.io.*;
import java.net.*;
import java.util.*;

public class Client extends Thread {
    final static String IP = "localhost";
    final static int PORT = 5555;
    
    ObjectInputStream sInput;
    ObjectOutputStream sOutput;
    Socket socketServer;
    
    float a, b;
    
    public Client(float a, float b) {
        this.a = a;
        this.b = b;
    }
    
    public void run() {
        Object[] p_ = {a, b};
        Object res = RPC.callMethod(IP, PORT, new CallInfo("Calculator", "sumar", p_));
        System.out.println("Respuesta: " + res);
        System.out.println("Resta: " + RPC.callMethod(IP, PORT, new CallInfo("Calculator", "restar", p_)));
        System.out.println("Saldo deposito: " + RPC.callMethod(IP, PORT, new CallInfo("Bank", "depositar", p_)));        
    }
    
    public static void main(String[] args) {
        new Client(5, 3).start();
        new Client(1, 2).start();
    }    
}
