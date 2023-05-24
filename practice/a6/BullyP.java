import java.util.Scanner;

public class BullyP {

  public static boolean states[];
  public static int coordinator;

  public static void init() {
    states = new boolean[5];
    System.out.println("THE SYSTEM HAS 5 processes ID's FROM 0 to 4");
    for (int i = 0; i < 5; i++) {
      states[i] = true;
    }
    coordinator = 4;
  }

  public static void getStatus() {
    System.out.println("\t\tCURRENT STATE");
    for (int i = 0; i < states.length; i++) {
      System.out.println(
        "Process ID[" +
        i +
        "]:\t" +
        (states[i] ? "UP" : "DOWN") +
        (coordinator == i ? "\t<- COORDINATOR" : "")
      );
    }
  }

  public static void processUp(int id) {
    if (!states[id]) {
      states[id] = true;
      System.out.println("Process " + id + " is now up");
    } else {
      System.out.println("Process is already UP");
    }
    electCoord(id);
  }

  public static void processDown(int id) {
    if (states[id]) {
      states[id] = false;
      System.out.println("Process " + id + " is now brought down");
    } else {
      System.out.println("Process is already DOWN");
    }
  }

  public static void electCoord(int id) {
    System.out.println("Election started by process: " + id);
    boolean flag = false;
    for (int i = id + 1; i < states.length; i++) {
      System.out.println("[ELECTION]: " + id + "->" + i);
      if (i == coordinator && states[coordinator]) {
        System.out.println("[ALIVE]: " + coordinator + "->" + id);
        flag = true;
        break;
      }
    }
    if (!flag) {
      for (int i = states.length - 1; i >= 0; i--) {
        if (states[i]) {
          System.out.println("[CO-ORDINATOR BROADCAST]: " + i + "->" + "ALL");
          coordinator = i;
          break;
        }
      }
    }
  }

  public static void sendMsg(int idSender, int idReceiver) {
    if (states[coordinator]) {
      if (states[idSender] && states[idReceiver]) {
        System.out.println("Message was sent successfully");
      } else if (!states[idSender]) {
        System.out.println("Sender is down");
      } else {
        System.out.println("Receiver is down");
      }
    } else {
      System.out.println("Co-ordinator is down, conducting election");
      electCoord(idSender);
      sendMsg(idSender, idReceiver);
    }
  }

  public static void main(String args[]) {
    init();
    int choice, id, sender, receiver;
    Scanner sc = new Scanner(System.in);
    do {
      getStatus();
      System.out.println(
        "MENU\n1.Bring process UP\n2.Bring process DOWN\n3.Send Message\n4.Exit"
      );
      choice = sc.nextInt();
      switch (choice) {
        case 1:
          System.out.println("Enter process ID: ");
          id = sc.nextInt();
          processUp(id);
          break;
        case 2:
          System.out.println("Enter process ID: ");
          id = sc.nextInt();
          processDown(id);
          break;
        case 3:
          System.out.println("Enter sender process ID: ");
          sender = sc.nextInt();
          System.out.println("Enter receiver process ID: ");
          receiver = sc.nextInt();
          sendMsg(sender, receiver);
          break;
        case 4:
          break;
        default:
          System.out.println("Enter a valid value!!");
          break;
      }
    } while (choice != 4);
  }
}
