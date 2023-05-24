import java.util.Scanner;

class ElectionToken{
    public int originalInitiator;
    public int initiator;
    public int priority;

    public ElectionToken(int id, int initPriority){
        originalInitiator = id;
        initiator = id;
        priority = initPriority;
    }
}

public class ring{
    public static int[] network;
    Scanner sc = new Scanner(System.in);

    //methodelection sathi
    public static void elect(ElectionToken et){
        for(int i = et.originalInitiator+1; 
        (i% network.length) != et.originalInitiator;
        i++){
            //adhi token kuthe ahe te display kraycha
                System.out.println("Token at" + i % network.length);
            if(network[i%network.length] > et.priority){
                et.initiator = i % network.length;
                et.priority = network[i % network.length];
                System.out.println("[Change] node has higher priority");
            }
            System.out.println("Token at: " + et.originalInitiator);
            System.out.println("Election Result:");
            if(et.originalInitiator != et.initiator){
                System.out.println("Token initiater has been changed");
            }else{
                System.out.println("Token initiator is the same");
            }
            System.out.println("Node "+ et.initiator + " with priority "+ et.priority+ " is the coordinator");
        }
    }

    //no of nodes cha input ghyaycha ani tyanna priority value assign kraychi and node ani ti value display kraychi
    public static void initNetwork(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of nodes: ");
        int nodes = sc.nextInt();
        network = new int[nodes];
        for(int i=0; i< nodes; i++){
            System.out.println("Enter the priority value for node [" + i +"]:");
            network[i] = sc.nextInt();
        }

        System.out.println("The network is initialized as follows:");
        for(int i =0; i<nodes; i++){
            System.out.print("["+ i + "]:" + network[i]+ "->" );
        }
        System.out.println("[0]:"+ network[0]);
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        initNetwork();
        System.out.println("Select the process ID to start the election: ");
        int id = sc.nextInt();

        ElectionToken ET = new ElectionToken(id, network[id]);
        elect(ET);
    }
}
