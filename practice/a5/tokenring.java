import java.util.*;
import java.util.concurrent.TimeUnit;

class tokenring{

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        //get and display all nodes
        System.out.print("Enter the no of nodes: ");
        int node = scan.nextInt();
        for(int i=0; i<node; i++){
            System.out.print(i + " ");
        }
        System.out.println(""+ 0);

        //get sender, receiver, data
        int token = 0;
        System.out.print("Enter the sender node: ");
        int sender = scan.nextInt();
        System.out.println("Enter the reveiver node: ");
        int receiver = scan.nextInt();
        System.out.println("Enter data: ");
        String data = scan.next();

        //keep passing the token until the sender is found
        for( int i = token, j = token;
        (i % node) != sender;
        i++, j = (j + 1) % node){
            System.out.println(" "+ j + "->");
        }

        //sender sending the data
        System.out.println("sender sending to node" + receiver);
        for(int i=sender+1; i != receiver+1; i=(i+1)% node){
            System.out.print("data "+ i+ "->");
        }
    }
}