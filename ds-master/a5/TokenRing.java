import java.util.Scanner;

public class TokenRing{
	/**
	 * This Java function simulates token passing in a ring network and allows the user to send data
	 * between nodes.
	 */
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Number of Nodes you want in the Ring: ");
		int n = sc.nextInt();		
		System.out.println("");
		System.out.println("Ring Formed is as below: ");
		
		for(int i=0; i<n; i++){
			System.out.print(i + " ");
		}
		System.out.println("0");
		System.out.println("");
		int choice = 0;
		
		do{
			System.out.println("Enter Sender: ");
			int sender = sc.nextInt();
			
			System.out.println("Enter Receiver: ");
			int receiver = sc.nextInt();
			sc.nextLine();
			
			System.out.print("Enter data to be send: ");
			String data = sc.nextLine();
			System.out.println("");
			int token = 0;
			
			System.out.print("Token Passing: ");
			
			for(int i = token; i < receiver; i++){
				System.out.print(" " + i + " ->");
			} 
			
			System.out.println(" " + receiver);
			System.out.println("Sender: " + sender + " , Sending Data: " + data);
			
			for(int i = sender; i != receiver; i=(i+1)%n){
				System.out.println("Data: " + data + " , Forwarded By: " + i);
			}
			
			System.out.println("Receiver: " + receiver + " , Received the Data: " + data);
			
			token = sender;
			System.out.println("");
			System.out.print("Do you want to send data again? If YES enter 1, If NO enter 0: ");
			choice = sc.nextInt();
			
		} while(choice==1);
		
		sc.close();
	}
}
