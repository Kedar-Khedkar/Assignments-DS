import java.util.Scanner;

class ElectionToken {

  public int originalInitiator;
  public int initiator;
  public int priority;

  public ElectionToken(int id, int initPriority) {
    originalInitiator = id;
    initiator = id;
    priority = initPriority;
  }
}

public class Ring {

  public static int[] network;
  public static Scanner sc = new Scanner(System.in);

  public static void elect(ElectionToken et) {
    for (
      int i = et.originalInitiator + 1;
      (i % network.length) != et.originalInitiator;
      i++
    ) {
      System.out.println("[TOKEN AT]:" + i % network.length);
      if (network[i % network.length] > et.priority) {
        et.initiator = i % network.length;
        et.priority = network[i % network.length];
        System.out.println(
          "[CHANGE]:node has higher priority initiator changed"
        );
      }
    }
    System.out.println("[TOKEN AT]:" + et.originalInitiator);
    System.out.println("[ELECTION RESULT]:");
    if (et.originalInitiator != et.initiator) {
      System.out.println("Token initiator has been changed,");
    } else {
      System.out.println("Token initiator is the same,");
    }
    System.out.println(
      "Hence, node[" +
      et.initiator +
      "] with priority:" +
      et.priority +
      " has been elected as the co-ordinator"
    );
  }

  public static void initNetwork() {
    System.out.println("Enter the number of nodes: ");
    int nodes = sc.nextInt();
    network = new int[nodes];
    for (int i = 0; i < nodes; i++) {
      System.out.println("Enter the Priority value for node[" + i + "]: ");
      network[i] = sc.nextInt();
    }
    System.out.println("The network is initialized as follows:");
    for (int i = 0; i < nodes; i++) {
      System.out.print("[" + i + "]:" + network[i] + "->");
    }
    System.out.print("[0]:" + network[0] + "\n");
  }

  public static void main(String args[]) {
    initNetwork();
    int choice;
    do {
      System.out.println("MENU\n1.Start Election\n2.Quit\n");
      choice = sc.nextInt();
      int id;
      switch (choice) {
        case 1:
          System.out.println(
            "Enter the ID of the process which starts election: "
          );
          id = sc.nextInt();
          ElectionToken ET = new ElectionToken(id, network[id]);
          elect(ET);
          break;
        case 2:
          break;
        default:
          System.out.println("Enter a valid value!!!");
          break;
      }
    } while (choice != 2);
  }
}
