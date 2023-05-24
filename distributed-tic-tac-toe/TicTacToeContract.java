import java.rmi.*;
import java.util.*;
public interface TicTacToeContract extends Remote{
// These are the method signatures of the remote interface `TicTacToeContract` which defines the
// methods that can be called remotely by a client in a Tic Tac Toe game. Each method has a specific
// purpose such as registering a player, assigning a game, checking if it's a player's turn, making a
// move, validating the board, and retrieving the current state of the board. The `RemoteException` is
// thrown to handle any communication errors that may occur during the remote method invocation.

    public List<Integer> registerPlayer() throws RemoteException;
    public Integer assignGame(Integer playerID,Integer gameID) throws RemoteException;
    public Integer isItMyTurn(Integer gameID,Integer playerID,Integer opponentID) throws RemoteException;
    public void toggleTurn(Integer gameID,Integer playerID,Integer opponentID) throws RemoteException;
    public String registerMove(Integer gameID,Integer cell_number,Integer playerID) throws RemoteException;
    public String validateBoard(Integer gameID,Integer cell_number,Integer playerID) throws RemoteException;
    public void playersResponse(Integer gameID,Integer ans) throws RemoteException;
    public Integer continueGame(Integer gameID,Integer playerID) throws RemoteException;
    public boolean madeMove(Integer gameID,Integer playerID) throws RemoteException;
    public String retrieveBoard(Integer gameID) throws RemoteException;
}