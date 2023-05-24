package Client;

import java.rmi.Naming;
import java.util.Scanner;
import Server.ServerInterface;
/**
 * This Java class registers a client with a server and broadcasts a message to all registered clients.
 */

public class ClientMain {
    public static void main(String[] args) {
        try {
           // This code is registering a client with a server using Java RMI (Remote Method Invocation)
           // and broadcasting a message to all registered clients.
            ServerInterface server = (ServerInterface) Naming.lookup("rmi://localhost/Server");
            ClientInterface client = new ClientImplementation();
            server.registerClient(client);

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a message to broadcast: ");
            String message = scanner.nextLine();

            server.broadcastMessage(message);
        } catch (Exception e) {
            System.out.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
