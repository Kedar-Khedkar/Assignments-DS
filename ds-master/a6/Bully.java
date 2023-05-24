import java.util.Scanner;

public class Bully {
    static boolean[] state = new boolean[5];
    public static int coordinator = 4;

   /**
    * The function prints the current system state, including whether each process is up or down and
    * which process is the coordinator.
    */
    public static void getStatus() {
        System.out.println("\n+------Current System State-----+");
        for (int i = 0; i < state.length; i++) {
            System.out.println("| P" + (i + 1) + ":\t" + (state[i] ? "UP" : "DOWN") +
                    (coordinator == i ? "\t<-- COORDINATOR\t|" : "\t\t\t|"));
        }
        System.out.println("+-------------------------------+");
    }

   /**
    * The function checks if a process is already up and if not, holds an election and sets the
    * coordinator.
    * 
    * @param up The parameter "up" is an integer representing the process number that needs to be
    * brought up or activated.
    */
    public static void up(int up) {
        if (state[up - 1]) {
            System.out.println("Process " + up + " is already up");
        } else {
            state[up - 1] = true;
            System.out.println("--------Process " + up + " held election-------");
            for (int i = up; i < state.length; ++i) {
                System.out.println("Election message sent from process " + up + " to process " + (i + 1));
            }
            for (int i = state.length - 1; i >= 0; --i) {
                if (state[i]) {
                    coordinator = i;
                    break;
                }
            }
        }
    }

/**
 * The "down" function sets a process state to "down" and updates the coordinator if necessary.
 * 
 * @param down The parameter "down" is an integer representing the process number that needs to be
 * brought down.
 */
    public static void down(int down) {
        if (!state[down - 1]) {
            System.out.println("Process " + down + " is already down.");
        } else {
            state[down - 1] = false;
            if (coordinator == down - 1) {
                setCoordinator();
            }
        }
    }

/**
 * The function checks if the coordinator is alive and initiates an election if it is down.
 * 
 * @param mess The parameter "mess" is an integer representing the process number that is sending a
 * message.
 */
    public static void mess(int mess) {
        if (state[mess - 1]) {
            if (state[coordinator]) {
                System.out.println("Message Sent: Coordinator is alive");
            } else {
                System.out.println("Coordinator is down");
                System.out.println("Process " + mess + " initiated election");
                for (int i = mess; i < state.length; ++i) {
                    System.out.println("Election sent from process " + mess + " to process " + (i + 1));
                }
                setCoordinator();
            }
        } else {
            System.out.println("Process " + mess + " is down");
        }
    }

/**
 * This function sets the coordinator variable to the index of the last true value in the state array.
 */
    public static void setCoordinator() {
        for (int i = state.length - 1; i >= 0; i--) {
            if (state[i]) {
                coordinator = i;
                break;
            }
        }
    }

    /**
     * The function presents a menu to activate, deactivate, or send messages between processes in a
     * distributed system.
     */
    public static void main(String[] args) {
        int choice;
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < state.length; ++i) {
            state[i] = true;
        }
        getStatus();
        do {
            System.out.println("+........MENU........+");
            System.out.println("1. Activate a process.");
            System.out.println("2. Deactivate a process.");
            System.out.println("3. Send a message.");
            System.out.println("4. Exit.");
            System.out.println("+....................+");
            choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("Activate process:");
                    int up = sc.nextInt();
                    if (up == 5) {
                        System.out.println("Process 5 is the coordinator");
                        state[4] = true;
                        coordinator = 4;
                        break;
                    }
                    up(up);
                    break;
                }
                case 2: {
                    System.out.println("Deactivate process:");
                    int down = sc.nextInt();
                    down(down);
                    break;
                }
                case 3: {
                    System.out.println("Send message from process:");
                    int mess = sc.nextInt();
                    mess(mess);
                    break;
                }
            }
            getStatus();
        } while (choice != 4);
        sc.close();
    }
}

