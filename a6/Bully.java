import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Bully {

  // The code is defining and initializing two static variables `state` and `coordinator`. `state` is
  // an array of boolean values with a length of 5, representing the state of each process in the
  // system (whether it is up or down). `coordinator` is an integer variable initialized to 4,
  // representing the process ID of the current coordinator.
  static boolean[] state = new boolean[5];
  public static int coordinator = 4;

  public static void getStatus() {
    System.out.println("\n+------Current System State-----+");
    for (int i = 0; i < state.length; i++) {
      System.out.println(
        "| P" +
        (i + 1) +
        ":\t" +
        (state[i] ? "UP" : "DOWN") +
        (coordinator == i ? "\t<-- COORDINATOR\t|" : "\t\t\t|")
      );
    }
    System.out.println("+-------------------------------+");
  }

  public static void setCoordinator() {
    for (int i = 4; i >= 0; i--) {
      if (state[i]) {
        coordinator = i;
        break;
      }
    }
  }

 /**
  * The "up" function checks if a process is already up and if not, holds an election and sends
  * messages to other processes.
  * 
  * @param up The parameter "up" is an integer representing the process number that is being brought up
  * or activated.
  */
  public static void up(int up) throws Throwable {
    if (state[up - 1]) {
      System.out.println("Process " + up + " is already up");
    } else {
      int i;
      Bully.state[up - 1] = true;
      System.out.println("--------Process " + up + " held election-------");
      for (i = up; i < 5; ++i) {
        System.out.println(
          "Election message sent from process " + up + " to process " + (i + 1)
        );
      }
      TimeUnit.SECONDS.sleep(1);
      for (i = up + 1; i <= 5; ++i) {
        if (!state[i - 1]) continue;
        System.out.println(
          "Alive message sent from process " + i + " to process " + up
        );
        break;
      }
    }
  }

 /**
  * The "down" function checks if a process is already down and sets its state to false if it is not.
  * 
  * @param down The parameter "down" is an integer representing the process number that needs to be
  * brought down.
  */
  public static void down(int down) {
    if (!state[down - 1]) {
      System.out.println("Process " + down + "is already down.");
    } else {
      Bully.state[down - 1] = false;
    }
  }

 /**
  * The function sends an election message to other processes and sets a new coordinator if the current
  * coordinator is down.
  * 
  * @param mess an integer representing the process number that is sending the message.
  */
  public static void mess(int mess) throws Throwable {
    if (state[mess - 1]) {
      if (state[coordinator]) {
        System.out.println("Message Sent Co-ordinator is alive");
      } else if (!state[coordinator]) {
        int i;
        System.out.println("Co-ordinator is down");
        System.out.println("Process " + mess + " election");
        TimeUnit.SECONDS.sleep(1);
        for (i = mess; i < 5; ++i) {
          System.out.println(
            "Election sent from process " + mess + " to process " + (i + 1)
          );
        }
        for (i = 5; i >= mess; --i) {
          if (!state[i - 1]) continue;
          TimeUnit.SECONDS.sleep(1);
          System.out.println(
            "Coordinator message send from process " + i + " to all "
          );
          Bully.setCoordinator();
          break;
        }
        TimeUnit.SECONDS.sleep(1);
      }
    } else {
      System.out.println("Proccess" + mess + "is down");
    }
  }

  // The `main` method is the entry point of the program. It initializes the `state` array with all
  // processes set to `true` (up), prints the current system state using the `getStatus` method, and
  // then enters a loop that displays a menu of options to the user and prompts for input. The user can
  // choose to bring a process up, bring a process down, or send a message. The loop continues until
  // the user chooses to exit. The `Scanner` class is used to read user input from the console. The
  // `getStatus` method is called after each user action to display the updated system state. The
  // `throws Throwable` clause indicates that the method may throw any type of exception.
  public static void main(String[] args) throws Throwable {
    int choice;
    Scanner sc = new Scanner(System.in);
    for (int i = 0; i < 5; ++i) {
      Bully.state[i] = true;
    }
    Bully.getStatus();
    do {
      System.out.println("+........MENU........+");
      System.out.println("1.Up a process.");
      System.out.println("2.Down a process.");
      System.out.println("3 Send a message.");
      System.out.println("4.Exit.");
      System.out.println("+....................+");
      choice = sc.nextInt();
      switch (choice) {
        case 1:
          {
            System.out.println("Bring process up:");
            int up = sc.nextInt();
            if (up == 5) {
              System.out.println("process 5 is co-ordinator");
              Bully.state[4] = true;
              coordinator = 4;
              break;
            }
            Bully.up(up);
            break;
          }
        case 2:
          {
            System.out.println("Bring down any process:");
            int down = sc.nextInt();
            Bully.down(down);
            break;
          }
        case 3:
          {
            System.out.println("Which process will send message?");
            int mess = sc.nextInt();
            Bully.mess(mess);
          }
      }
      Bully.getStatus();
    } while (choice != 4);
    sc.close();
  }
}
