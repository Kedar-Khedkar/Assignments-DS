import java.util.ArrayList;
import java.util.Scanner;

/**
 * The tokenRing class initializes a network and prints the nodes in a ring topology.
 */
public class tokenRing {

  static Scanner sc = new Scanner(System.in);

  public static int initNetwork() {
    System.out.println("Enter the number of nodes: ");
    int n = sc.nextInt();
    for (int i = 0; i < n; i++) {
      System.out.printf("%d ", i);
    }
    System.out.print("0");
    return n;
  }

/**
 * The function simulates passing a token between nodes in a ring network.
 * 
 * @param reciever The receiver parameter is an integer representing the index of the process that is
 * intended to receive the token.
 * @param token The token parameter represents the current position of the token in the token passing
 * algorithm. It is an integer value that is used to determine which process should receive the token
 * next.
 * @param n The total number of nodes in the network.
 * @return The method is returning an integer value which is the receiver of the token.
 */
  public static int passToken(int reciever, int token, int n) {
    System.out.println("-:TOKEN PASSING:-");
    for (int i = token; i % n != reciever; i++) {
      System.out.print((i % n) + "->");
    }
    System.out.print("" + reciever + "\n");
    return reciever;
  }
// The `main` method is the entry point of the program. It initializes the network by calling the
// `initNetwork` method and then prompts the user to enter the sender, receiver, and data for the
// message to be sent. It then calls the `passToken` method twice to simulate passing the token from
// the sender to the receiver and back. Finally, it prints out the data received by the receiver.

  public static void main(String args[]) {
    int nodes = initNetwork();
    int sender, reciever, token = 0;
    String data;
    do {
      System.out.println("Enter the sender: ");
      sender = sc.nextInt();
      System.out.println("Enter the reciever: ");
      reciever = sc.nextInt();
      System.out.println("Enter the data: ");
      data = sc.next();
    } while (
      !(
        (sender > 0 && sender < nodes) &&
        (reciever > 0 && reciever < nodes) &&
        (reciever != sender)
      )
    );
    token = passToken(sender, token, nodes);
    System.out.println("==:TOKEN WITH SENDER, SENDING DATA:==");
    token = passToken(reciever, token, nodes);
    System.out.println("DATA Recieved by " + reciever + ": " + data);
  }
}
