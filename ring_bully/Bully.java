import java.util.Scanner;

public class Bully {

  public static boolean states[];
  public static int coordinator;

 // The `init()` method initializes the system by creating an array of boolean values representing the
 // states of the processes. It sets all the values to `true` to indicate that all processes are
 // initially up. It also sets the `coordinator` variable to 4, indicating that process 4 is the
 // initial coordinator. The `getStatus()` method prints the current state of the system by iterating
 // over the `states` array and printing the status of each process. If a process is the coordinator,
 // it is indicated by an arrow pointing to it.
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

// These are two methods in the Bully class that are used to bring a process up or down respectively.
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

/**
 * The function elects a coordinator among a group of processes.
 * 
 * @param id The id parameter represents the process id of the process that is initiating the election.
 */
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

/**
 * The function sends a message between two nodes and handles cases where the coordinator or either
 * node is down.
 * 
 * @param idSender The ID of the sender who is trying to send the message.
 * @param idReceiver The ID of the receiver who is supposed to receive the message.
 */
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

  // This is the main method of the Bully algorithm implementation in Java. It initializes the system,
  // takes user input to bring processes up or down, and to send messages between processes. It uses a
  // menu-driven approach to take user input and calls the appropriate methods to perform the desired
  // action. The program runs in a loop until the user chooses to exit.
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
