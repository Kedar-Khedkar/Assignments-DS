import java.rmi.*;  
public class AddClient
{  
	public static void main(String args[])
	{  
		try
		{  
			Addition stub=(Addition)Naming.lookup("rmi://localhost:5252/sum-server");  
			System.out.println(stub.add(7,4));  
		}
		catch(Exception e)
		{
		}  
	}  
}  
