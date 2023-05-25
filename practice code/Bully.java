import java.util.Scanner;

class bully{
    //state chi array and coordinator
    public static boolean states[];
    public static int coordinator;

    //set all states as true by default &bcoordinator as 4
    public static void init(){
        states = new boolean[5];
        System.out.println("The system has 5 processes IDs from 0 to 4");
        for(int i=0; i < 5; i++){
            states[i] = true;
        }
        coordinator = 4;
    }

    //get the status of all the processes
    public static void getStatus(){
        System.out.println("\t\t The current status");
        for(int i=0; i<states.length; i++){
            System.out.println(
                "P" + i + ": " + (states[i] ? "UP" : "Down") + (coordinator == i ? "\t<-Coordinator": "")
            );
        }
    }

    //check if the process is up nasel up tr up kraycha
    public static void processUp(int id){
        if(!states[id]){
            states[id] = true;
            System.out.println("Process "+ id + " is up now");
        }else{
            System.out.println("Process is already up");
        }
        electcord(id);
    }

    //process down
    public static void processDown(int id){
        if(states[id]){
            states[id] = false;
            System.out.println("Process "+ id+ " is now brought down");
        }else{
            System.out.println("Process is already down");
        }
    }

    //electcoordinator pahile flag flase thevaycha, ani tya process pasun subsequent process la election cha msg send kraycha, jr i coordinator asel ani up asel tr to jyani election initiate kelay tyala live asa msg pathvel
    public static void electcord(int id){
        System.out.println("Election process initiated by " + id);
        boolean flag = false;
        for(int i = id + 1; i < states.length; i++){
            System.out.println("Election" + id + "->" + i);
            if(i == coordinator && states[coordinator]){
                System.out.println("ALive"+ coordinator+ "->"+ id );
                flag = true;
            break;
            }

        };
        //flag jr false asel tr states madhe ulta travel kraycha ani ji 1st process up asel ti coordinator asel
        if(!flag){
            for(int i=states.length-1; i >= 0; i--){
                if(states[i]){
                    System.out.println("Broadcast coordinator"+ id + "-> all");
                    coordinator = i;
                    break;
                }
            }

        }
    }
    //coordinator up asel tr sender to receiver msg jail and nasel tr coordinator elect kraycha
    public static void sendmsg(int idSender, int idReceiver){
        if(states[coordinator]){
            if(states[idSender] && states[idReceiver]){
                System.out.println("Message Sent Successfully");
            }else if(!states[idSender]){
                System.out.println("Sender is down");
            }else{
                System.out.println("Receiver is down");
            }
        }else{
            System.out.println("Coordinator is down conducting election");
            electcord(idSender);
            sendmsg(idSender, idReceiver);
        }
    }

    public static void main(String[] args){
        init();
        int choice, id, sender,receiver;
        Scanner sc = new Scanner(System.in);
        do{
            getStatus();
            System.out.println("1.Process Up\n2.Process Down\n3.Send Message\4.Exit");
            choice = sc.nextInt();
            switch(choice){
                case 1:
                System.out.println("Enter the process up id");
                id = sc.nextInt();
                processUp(id);
                break;

                case 2:
                System.out.println("Enter the process down id");
                id = sc.nextInt();
                processDown(id);
                break;

                case 3:
                System.out.println("Enter the sender id");
                sender = sc.nextInt();
                System.out.println("Enter the receiver id");
                receiver = sc.nextInt();
                sendmsg(sender,receiver);
                break;

                case 4:
                break;

                default:
                System.out.println("Enter valid choice");
            } ;
        }while(choice != 4);
    }
}