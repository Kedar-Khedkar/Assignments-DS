package Server;
import java.rmi.Remote;
import java.rmi.RemoteException;

import Client.ClientInterface;

// This is a Java interface for a remote server that extends the `Remote` interface, indicating that it
// can be accessed remotely. It declares two methods that can be called by clients: `registerClient`
// and `broadcastMessage`.
public interface ServerInterface extends Remote {
    void registerClient(ClientInterface client) throws RemoteException;
    void broadcastMessage(String message) throws RemoteException;
}
