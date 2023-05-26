import java.io.*;
import java.net.*;

import RPC.*;

public class Server {
    final static int PORT = 5555;
    
    public void start() {
        try {
            ServerSocket socket = new ServerSocket(PORT);
            System.out.println("Puerto " + PORT);
            RPC.addService("Calculator", new CalculatorImpl()); //Registro de nombres
            RPC.addService("Bank", new BancoImpl());
            while(true) {
                Socket clientSocket = socket.accept();
                System.out.println("Cliente aceptado");
                Thread t = new ClientThread(clientSocket); //Gestionar comunicación
                t.start();
                System.out.println("Cliente iniciado");
            }
        }
        catch(Exception e) {};
    }
    
    public static void main(String[] args) {
        new Server().start();
    }
    
    class ClientThread extends Thread {
        Socket socket; //Cliente
        ObjectInputStream sInput;
        ObjectOutputStream sOuput;
        
        ClientThread(Socket s){
            this.socket = s;
            try {
                sInput = new ObjectInputStream(s.getInputStream());
                sOuput = new ObjectOutputStream(s.getOutputStream());    
            }
            catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        
        public void run() {
            try {
                CallInfo ci = (CallInfo) sInput.readObject();
                Object res = RPC.execService(ci);
                sOuput.writeObject(res);
            }
            catch(Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
