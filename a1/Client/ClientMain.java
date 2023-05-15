package Client;

import java.rmi.Naming;
import java.util.Scanner;
import Server.ServerInterface;

public class ClientMain {
    public static void main(String[] args) {
        try {
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
