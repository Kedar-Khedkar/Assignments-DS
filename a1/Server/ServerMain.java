package Server;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServerMain {
    public static void main(String[] args) {
        try {
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
