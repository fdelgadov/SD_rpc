import java.io.*;
import java.net.*;
import RPC.*;

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
        Object res = RPC.callMethod(IP, PORT, new CallInfo("Calculator", "sumar", p_)); //Cliente -> Servidor
        System.out.println("Respuesta: " + res);
        System.out.println("Resta: " + RPC.callMethod(IP, PORT, new CallInfo("Calculator", "restar", p_)));
        Object[] s_ = {(float) 3000};
        System.out.println("Saldo deposito: " + RPC.callMethod(IP, PORT, new CallInfo("Bank", "depositar", s_)));
        s_[0] = (float) 2000;
        System.out.println("Saldo deposito: " + RPC.callMethod(IP, PORT, new CallInfo("Bank", "retirar", s_)));
    }
    
    public static void main(String[] args) {
        new Client(5, 3).start();
        new Client(1, 2).start();
    }    
}
