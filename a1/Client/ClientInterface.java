package Client;
import java.rmi.Remote;
import java.rmi.RemoteException;

// This code is defining a remote interface called `ClientInterface` that extends the `Remote`
// interface. It declares a single method called `receiveMessage` that takes a `String` parameter and
// throws a `RemoteException`. This interface is used to define the methods that can be called remotely
// by a server in a distributed system.
public interface ClientInterface extends Remote {
    void receiveMessage(String message) throws RemoteException;
}
