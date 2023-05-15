package Server;
import java.rmi.Remote;
import java.rmi.RemoteException;

import Client.ClientInterface;

public interface ServerInterface extends Remote {
    void registerClient(ClientInterface client) throws RemoteException;
    void broadcastMessage(String message) throws RemoteException;
}
