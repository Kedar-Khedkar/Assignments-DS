package Server;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
/**
 * This Java class starts a server using RMI (Remote Method Invocation) technology.
 */

public class ServerMain {
    public static void main(String[] args) {
        try {
           // This code is starting a server using RMI (Remote Method Invocation) technology.
            ServerInterface server = new ServerImplementation();
            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://localhost/Server", server);
            System.out.println("Server started.");
        } catch (Exception e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
