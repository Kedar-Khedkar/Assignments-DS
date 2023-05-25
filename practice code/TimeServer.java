import java.util.List;
import java.util.ArrayList;

//class TimeServer

class TimeServer{
    private List<Integer> clocks;

    public TimeServer(List<Integer> clocks){
        this.clocks = clocks;
    };

    public void synchronizeClocks(){
        int sum = 0;
        int average;

        //calc sum of all clocks
        for(int clock: clocks){
            sum += clock;
        };

        //calc the average
        average = sum/ clocks.size();

        //assign avg to all the clocks
        for(int i=0; i<clocks.size(); i++){
            clocks.set(i, average);
        }
    }

    //return the list of clocks
    public List<Integer> getClocks(){
        return clocks;
    };

    //main class
    public static void main(String[] args){
        //create a list of clocks with their initial values
        List<Integer> clocks = new ArrayList<>();
        clocks.add(100);
        clocks.add(200);
        clocks.add(150);
        clocks.add(300);

        TimeServer timeServer = new TimeServer(clocks);

        //synchronize the clocks using berkeleys algo
        timeServer.synchronizeClocks();

        //get the synchronized clocks in the list
        List<Integer> synchronizedClocks = timeServer.getClocks();

        //print the synchronizedclocks
        System.out.println("Synchronized clock time:");
        for(int clock:synchronizedClocks){
            System.out.println(clock);
        }
    };
};