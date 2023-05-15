package Server;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * This Java class implements a server interface that allows clients to register and broadcast messages
 * to all registered clients.
 */
public class ServerImplementation extends UnicastRemoteObject implements ServerInterface {
    private List<Client.ClientInterface> clients;

    public ServerImplementation() throws RemoteException {
        clients = new ArrayList<>();
    }

    public void registerClient(Client.ClientInterface client) throws RemoteException {
        clients.add(client);
    }

    public void broadcastMessage(String message) throws RemoteException {
        System.out.println("Broadcasting message: " + message);
        for (Client.ClientInterface client : clients) {
            client.receiveMessage(message);
        }
    }
}
