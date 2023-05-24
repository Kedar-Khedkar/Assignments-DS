import java.rmi.*;  
import java.rmi.server.*;  
public class AddImplementor extends UnicastRemoteObject implements Addition
{  
	AddImplementor()throws RemoteException
	{  
		super();  
	}  
	public int add(int x,int y)
	{
		return x+y;
	}  
}  
