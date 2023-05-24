import java.util.*;
import java.util.concurrent.TimeUnit;

class tokenring {

// This is the main method of a Java program that simulates a token ring network. It takes user input
// for the number of nodes in the network, the sender and receiver nodes, and the data to be
// transmitted. It then passes a token around the network until it reaches the sender node, at which
// point the sender node sends the data to the receiver node. The program uses a for loop and the
// TimeUnit class to simulate the passing of the token and the transmission of the data. Finally, the
// program prints out a message indicating that the receiver node has received the data.
  public static void main(String args[]) throws Throwable {
    Scanner scan = new Scanner(System.in);

    //Get and print all nodes
    System.out.println("Enter the num of nodes:");
    int nodes = scan.nextInt();
    for (int i = 0; i < nodes; i++) {
      System.out.print(" " + i);
    }
    System.out.println(" " + 0);

    //Get sender, reciever and data, and initialize token to node 0
    int token = 0;
    int sender, reciever;
    System.out.println("Enter sender:");
    sender = scan.nextInt();
    System.out.println("Enter receiver:");
    reciever = scan.nextInt();
    System.out.println("Enter Data:");
    String data = scan.next();

    //Keep passing the token until sender is found
    System.out.print("Token passing:");
    for (
      int i = token, j = token;
      (i % nodes) != sender;
      i++, j = (j + 1) % nodes
    ) {
      System.out.print(" " + j + "->");
      TimeUnit.SECONDS.sleep(1);
    }
    System.out.println(" " + sender);

    System.out.println(
      "--------------------TOKEN WITH SENDER NOW PASSING DATA-------------------"
    );
    System.out.println("Sender " + sender + " sending data: " + data);
    for (int i = sender + 1; i != reciever + 1; i = (i + 1) % nodes) {
      System.out.print("data " + i + "->");
      TimeUnit.SECONDS.sleep(1);
    }
    System.out.println();
    System.out.println(
      "-------------------Receiver " +
      reciever +
      " received data: " +
      data +
      "----------------------\nodes"
    );
    token = sender;
    scan.close();
  }
}
