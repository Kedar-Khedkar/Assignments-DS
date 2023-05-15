package Client;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * This Java class implements the ClientInterface and defines a method to receive and print a message.
 */
public class ClientImplementation extends UnicastRemoteObject implements ClientInterface {
    protected ClientImplementation() throws RemoteException {
    }

    public void receiveMessage(String message) throws RemoteException {
        System.out.println("Received message: " + message);
    }
}
