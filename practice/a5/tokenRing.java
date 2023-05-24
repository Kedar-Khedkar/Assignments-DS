import java.util.ArrayList;
import java.util.Scanner;

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

  public static int passToken(int reciever, int token, int n) {
    System.out.println("-:TOKEN PASSING:-");
    for (int i = token; i % n != reciever; i++) {
      System.out.print((i % n) + "->");
    }
    System.out.print("" + reciever + "\n");
    return reciever;
  }

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
